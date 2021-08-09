package io.xiaowei.seckill.jpa;

import io.xiaowei.model.SecKillResultModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface SecKillResultJpa extends JpaRepository<SecKillResultModel, Long> {

}
