package io.xiaowei.seckill.service;

import io.xiaowei.model.ProductModel;
import io.xiaowei.model.SecKillModel;
import io.xiaowei.model.SecKillResultModel;
import io.xiaowei.seckill.jpa.SecKillJpa;
import io.xiaowei.seckill.jpa.SecKillResultJpa;
import io.xiaowei.seckill.openfeign.ProductFeign;
import io.xiaowei.seckill.req.SecKillProductReq;
import io.xiaowei.seckill.utils.RedisLockKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
@Slf4j
public class SecKillServiceImpl implements ISecKillService {

    @Resource
    private SecKillJpa secKillJpa;

    @Resource
    private SecKillResultJpa secKillResultJpa;

    @Resource
    private ProductFeign productFeign;

    private final Lock lock = new ReentrantLock();

    private final Map<Long, Future> cacheSecKillResultMap = new HashMap<Long, Future>();

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Resource
    private RedisLockKit redisLockKit;

    @Override
    public String saveSecKillProduct(SecKillProductReq secKillProductReq) {
        ProductModel productModel = productFeign.findById(secKillProductReq.getProductId());
        if (Objects.isNull(productModel)) {
            throw new RuntimeException("商品不存在");
        }
        SecKillModel secKillModel = new SecKillModel();
        secKillModel.setShopId(productModel.getShopId());
        secKillModel.setProductId(productModel.getId());
        secKillModel.setSecKillInventory(secKillProductReq.getSecKillInventory());
        secKillModel.setState(0);
        secKillModel.setSecKillNum(0);
        secKillModel.setProductPrice(productModel.getProductPrice());
        secKillModel.setStartTime(secKillProductReq.getStartTime());
        secKillModel.setEndTime(secKillProductReq.getEndTime());
        secKillModel.setSecKillPrice(secKillProductReq.getSecKillPrice());
        secKillModel.setProductName(productModel.getProductName());
        secKillModel.setProductTitle(secKillProductReq.getProductTitle());
        secKillJpa.saveAndFlush(secKillModel);
        return "SUCCESS";
    }

    @Override
    public List<SecKillModel> all() {
        return secKillJpa.findByState(1);
    }

    /**
     * Lock 程序锁
     *
     * @param id     秒杀ID
     * @param userId 用户ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> procedureByLock(Long id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        lock.lock();
        try {
            SecKillModel secKillModel = getSecKillModel(id);
            if (Objects.isNull(secKillModel)) {
                throw new RuntimeException("秒杀数据不存在");
            }
            /*库存*/
            long secKillInventory = secKillModel.getSecKillInventory();
            /*秒杀数量*/
            Integer secKillNum = secKillModel.getSecKillNum();
            secKillNum++;
            if (secKillNum > secKillInventory) {
                log.info(">>>>>卖光了,谢谢惠顾>>>>>");
                result.put("flag", "fail");
                result.put("data", "卖光了,谢谢惠顾");
                return result;
            }
            secKillModel.setSecKillNum(secKillNum);
            secKillJpa.saveAndFlush(secKillModel);
            result.put("flag", "success");
            result.put("data", "秒杀成功");
            log.info(">>>>>秒杀成功>>>>>");
        } catch (Exception e) {
            throw new RuntimeException("秒杀程序异常:信息" + e.getMessage() + "堆栈" + e);
        } finally {
            lock.unlock();
        }
        return result;
    }

    /**
     * Lock AOP
     *
     * @param id     秒杀ID
     * @param userId 用户ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> procedureByLockAop(Long id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        SecKillModel secKillModel = getSecKillModel(id);
        if (Objects.isNull(secKillModel)) {
            throw new RuntimeException("秒杀数据不存在");
        }
        /*库存*/
        long secKillInventory = secKillModel.getSecKillInventory();
        /*秒杀数量*/
        Integer secKillNum = secKillModel.getSecKillNum();
        secKillNum++;
        if (secKillNum > secKillInventory) {
            log.info(">>>>>卖光了,谢谢惠顾>>>>>");
            result.put("flag", "fail");
            result.put("data", "卖光了,谢谢惠顾");
            return result;
        }
        secKillModel.setSecKillNum(secKillNum);
        secKillJpa.saveAndFlush(secKillModel);
        result.put("flag", "success");
        result.put("data", "秒杀成功");
        log.info(">>>>>秒杀成功>>>>>");
        return result;
    }

    /**
     * 单实例秒杀（悲观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> pessimisticLock(Long id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        SecKillModel secKillModel = secKillJpa.findByIdAndState(id, 1);
        if (Objects.isNull(secKillModel)) {
            throw new RuntimeException("秒杀数据不存在");
        }
        /*库存*/
        long secKillInventory = secKillModel.getSecKillInventory();
        /*秒杀数量*/
        Integer secKillNum = secKillModel.getSecKillNum();
        secKillNum++;
        if (secKillNum > secKillInventory) {
            log.info(">>>>>卖光了,谢谢惠顾>>>>>");
            result.put("flag", "fail");
            result.put("data", "卖光了,谢谢惠顾");
            return result;
        }
        secKillModel.setSecKillNum(secKillNum);
        secKillJpa.saveAndFlush(secKillModel);
        result.put("flag", "success");
        result.put("data", "秒杀成功");
        log.info(">>>>>秒杀成功>>>>>");
        return result;
    }

    /**
     * 单实例秒杀（乐观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> optimisticLock(Long id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        SecKillModel secKillModel = getSecKillModel(id);
        if (Objects.isNull(secKillModel)) {
            throw new RuntimeException("秒杀数据不存在");
        }
        /*库存*/
        long secKillInventory = secKillModel.getSecKillInventory();
        /*秒杀数量*/
        Integer secKillNum = secKillModel.getSecKillNum();
        secKillNum++;
        if (secKillNum > secKillInventory) {
            log.info(">>>>>卖光了,谢谢惠顾>>>>>");
            result.put("flag", "fail");
            result.put("data", "卖光了,谢谢惠顾");
            return result;
        }
        secKillModel.setSecKillNum(secKillNum);
        int res = secKillJpa.updateSecKillWithVersion(secKillModel.getId(), secKillModel.getSecKillNum(), secKillModel.getVersion());
        if (res > 0) {
            result.put("flag", "success");
            result.put("data", "秒杀成功");
            log.info(">>>>>秒杀成功>>>>>");
        } else {
            result.put("flag", "fail");
            result.put("data", "秒杀失败");
            log.info(">>>>>秒杀失败>>>>>");
        }
        return result;
    }

    /**
     * 分布式锁（Redis）
     *
     * @param id     id
     * @param userId userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> redisDistributedLock(Long id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        boolean hasLock = redisLockKit.tryLock(id + "", 30);
        log.info("### Redis锁获取结果:{} ###", hasLock);
        if (hasLock) {
            getSkillData(id, result);
        } else {
            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                hasLock = redisLockKit.tryLock(id + "", 30);
                if (hasLock) {
                    getSkillData(id, result);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result.put("flag", "fail");
                result.put("data", "太火爆了，请重新抢购");
            }
        }
        redisLockKit.unlock(id + "");
        return result;
    }

    /**
     * Future异步
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public void secKillFuture(Long id, Long userId) throws ExecutionException, InterruptedException {
        Future<Integer> future = executorService.submit(new KillCallable(userId, id));
        cacheSecKillResultMap.put(userId, future);
    }


    class KillCallable implements Callable<Integer> {

        private Long userId;
        private Long id;

        public KillCallable(Long userId, Long id) {
            this.userId = userId;
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            SecKillResultModel secKillResultModel = new SecKillResultModel();
            SecKillModel secKillModel = getSecKillModel(id);
            if (Objects.isNull(secKillModel)) {
                throw new RuntimeException("秒杀数据不存在");
            }
            /*库存*/
            long secKillInventory = secKillModel.getSecKillInventory();
            /*秒杀数量*/
            Integer secKillNum = secKillModel.getSecKillNum();
            secKillNum++;
            secKillResultModel.setProductId(secKillModel.getProductId());
            secKillResultModel.setSecKillId(secKillModel.getId());
            secKillResultModel.setUserId(userId);
            secKillResultModel.setResult(0);
            secKillResultModel.setResultData("用户" + userId + "秒杀成功！！");
            secKillResultModel.setSecKillTime(new Date());
            if (secKillNum > secKillInventory) {
                log.info(">>>>>卖光了,谢谢惠顾>>>>>");
                secKillResultModel.setResult(1);
                secKillResultModel.setResultData("用户" + userId + "秒杀失败！！");
            } else {
                log.info(">>>>>秒杀成功:库存:{},当前第:{}个>>>>>", secKillInventory, secKillNum);
                secKillModel.setSecKillNum(secKillNum);
                secKillJpa.saveAndFlush(secKillModel);
            }
            secKillResultJpa.saveAndFlush(secKillResultModel);
            return secKillResultModel.getResult();
        }
    }


    /**
     * redis锁重试查询
     *
     * @param id
     * @param result
     */
    private void getSkillData(Long id, HashMap<String, Object> result) {
        SecKillModel secKillModel = getSecKillModel(id);
        if (Objects.isNull(secKillModel)) {
            throw new RuntimeException("秒杀数据不存在");
        }
        /*库存*/
        long secKillInventory = secKillModel.getSecKillInventory();
        /*秒杀数量*/
        Integer secKillNum = secKillModel.getSecKillNum();
        secKillNum++;
        log.info("### 当前库存:{} --- 当前秒杀数量:{} ###", secKillInventory, secKillNum);
        if (secKillNum > secKillInventory) {
            log.info(">>>>>卖光了,谢谢惠顾>>>>>");
            result.put("flag", "fail");
            result.put("data", "卖光了,谢谢惠顾");
            return;
        }
        secKillModel.setSecKillNum(secKillNum);
        try {
            secKillJpa.saveAndFlush(secKillModel);
            result.put("flag", "success");
            result.put("data", "秒杀成功");
            log.info(">>>>>秒杀成功>>>>>");
        } catch (Exception exception) {
            result.put("flag", "fail");
            result.put("data", "秒杀失败");
            log.info(">>>>>秒杀失败>>>>>");
        }

    }

    /**
     * 秒杀商品查询
     *
     * @param id id
     * @return
     */
    private SecKillModel getSecKillModel(Long id) {
        return secKillJpa.findById(id).orElse(null);
    }
}
