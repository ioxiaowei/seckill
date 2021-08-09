package io.xiaowei.user.jpa;

import io.xiaowei.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface UserJpa extends JpaRepository<UserModel, Long> {

}
