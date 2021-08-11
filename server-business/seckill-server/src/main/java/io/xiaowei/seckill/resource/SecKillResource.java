package io.xiaowei.seckill.resource;

import io.xiaowei.model.SecKillModel;
import io.xiaowei.seckill.req.SecKillProductReq;
import io.xiaowei.seckill.service.ISecKillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("sec/kill")
public class SecKillResource {

    @Resource
    private ISecKillService iSecKillService;

    /**
     * 秒杀列表
     *
     * @return
     */
    @GetMapping("all")
    public List<SecKillModel> all() {
        return iSecKillService.all();
    }

    /**
     * 设置秒杀商品
     *
     * @param secKillProductReq json数据体
     * @return
     */
    @PostMapping("save/product")
    public String saveSecKillProduct(@RequestBody SecKillProductReq secKillProductReq) {
        return iSecKillService.saveSecKillProduct(secKillProductReq);
    }

    /**
     * 单实例秒杀( Lock 程序锁)
     *
     * @param id     id
     * @param userId userId
     * @return
     */
    @PostMapping("lock/{id}/{userId}")
    public HashMap<String, Object> procedureLock(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return iSecKillService.procedureByLock(id, userId);
    }

    /**
     * 单实例秒杀( Lock 程序锁 + AOP+解决事务脏读问题+代码简化)
     *
     * @param id     id
     * @param userId userId
     * @return
     */
    @PostMapping("lock/aop/{id}/{userId}")
    public HashMap<String, Object> procedureLockAop(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return iSecKillService.procedureByLockAop(id, userId);
    }

    /**
     * 单实例秒杀（悲观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("pessimistic/lock/{id}/{userId}")
    public HashMap<String, Object> pessimisticLock(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return iSecKillService.pessimisticLock(id, userId);
    }

    /**
     * 单实例秒杀（乐观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("optimistic/lock/{id}/{userId}")
    public HashMap<String, Object> optimisticLock(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return iSecKillService.optimisticLock(id, userId);
    }

    /**
     * 分布式锁（Redis）
     *
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("redis/lock/{id}/{userId}")
    public HashMap<String, Object> redisDistributedLock(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        return iSecKillService.redisDistributedLock(id, userId);
    }
}
