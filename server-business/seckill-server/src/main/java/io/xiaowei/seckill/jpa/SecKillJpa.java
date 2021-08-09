package io.xiaowei.seckill.jpa;

import io.xiaowei.model.SecKillModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface SecKillJpa extends JpaRepository<SecKillModel, Long> {

    /**
     * 查询状态
     *
     * @param state 状态
     * @return
     */
    List<SecKillModel> findByState(Integer state);

    /**
     * 悲观锁
     *
     * @param id    id
     * @param state 状态
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    SecKillModel findByIdAndState(Long id, Integer state);

    /**
     * 乐观锁实现方案有两种 简单使用 @Version注解+Long version saveAndFlush
     * 写Query 防止和其他悲观锁业务重叠
     *
     * @param id         ID
     * @param secKillNum 数量
     * @param version    版本
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_sec_kill set sec_kill_num= :secKillNum, version = version + 1 where id = :id and version = :version", nativeQuery = true)
    int updateSecKillWithVersion(@Param("id") Long id, @Param("secKillNum") Integer secKillNum, @Param("version") Long version);

}
