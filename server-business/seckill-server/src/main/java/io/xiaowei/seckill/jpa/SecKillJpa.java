package io.xiaowei.seckill.jpa;

import io.xiaowei.model.SecKillModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface SecKillJpa extends JpaRepository<SecKillModel, Long> {

    List<SecKillModel> findByState(Integer state);

}
