//package io.xiaowei.seckill.utils;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.time.Duration;
//import java.util.Collections;
//
///**
// * 一并发就出错
// *
// * @author wangxiaowei
// * @apiNote Lock
// **/
//@Deprecated
//public class RedisLockUtil {
//
//    private static final String LOCK_SUCCESS = "OK";
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "PX";
//    private static final Long RELEASE_SUCCESS = 1L;
//
//
//    /**
//     * 尝试获取分布式锁
//     * <p>
//     * JEDIS      Redis客户端
//     *
//     * @param lockKey    锁
//     * @param requestId  请求标识
//     * @param expireTime 超期时间
//     * @return 是否获取成功
//     */
//    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
//        Jedis jedis = new Jedis("redis.ioxiaowei.com", 16379);
//        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//        if (LOCK_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 释放分布式锁
//     * <p>
//     * JEDIS     Redis客户端
//     *
//     * @param lockKey   锁
//     * @param requestId 请求标识
//     * @return 是否释放成功
//     */
//
//    public static boolean releaseDistributedLock(String lockKey, String requestId) {
//        Jedis jedis = new Jedis("redis.ioxiaowei.com", 16379);
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//        if (RELEASE_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;
//    }
//
//}
