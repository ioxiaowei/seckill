package io.xiaowei.seckill.service;

import io.xiaowei.model.SecKillModel;
import io.xiaowei.seckill.req.SecKillProductReq;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Service
 **/
public interface ISecKillService {

    String saveSecKillProduct(SecKillProductReq secKillProductReq);

    List<SecKillModel> all();

    /**
     * Lock程序锁
     *
     * @param id     秒杀ID
     * @param userId 用户ID
     * @return
     */
    HashMap<String, Object> procedureByLock(Long id, Long userId);

    /**
     * Lock By Aop
     *
     * @param id     秒杀ID
     * @param userId 用户ID
     * @return
     */
    HashMap<String, Object> procedureByLockAop(Long id, Long userId);

    /**
     * 单实例秒杀（悲观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    HashMap<String, Object> pessimisticLock(Long id, Long userId);

    /**
     * 单实例秒杀（乐观锁）
     *
     * @param id
     * @param userId
     * @return
     */
    HashMap<String, Object> optimisticLock(Long id, Long userId);
}
