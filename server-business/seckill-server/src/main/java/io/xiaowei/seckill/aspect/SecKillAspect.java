package io.xiaowei.seckill.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiaowei
 * @apiNote Aspect
 **/
@Aspect
@Slf4j
@Component
public class SecKillAspect {

    private final Lock lock = new ReentrantLock();

    @Pointcut("execution(public * io.xiaowei.seckill.service.SecKillServiceImpl.procedureByLockAop(*,*))")
    public void lockSecKill() {
    }

    @Around("lockSecKill()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        lock.lock();
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return obj;
    }

}
