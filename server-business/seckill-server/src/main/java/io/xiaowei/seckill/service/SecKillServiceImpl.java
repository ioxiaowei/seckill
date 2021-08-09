package io.xiaowei.seckill.service;

import io.xiaowei.model.ProductModel;
import io.xiaowei.model.SecKillModel;
import io.xiaowei.seckill.jpa.SecKillJpa;
import io.xiaowei.seckill.openfeign.ProductFeign;
import io.xiaowei.seckill.req.SecKillProductReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
    private ProductFeign productFeign;

    private final Lock lock = new ReentrantLock();

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
     * 秒杀商品查询
     *
     * @param id id
     * @return
     */
    private SecKillModel getSecKillModel(Long id) {
        return secKillJpa.findById(id).orElse(null);
    }
}
