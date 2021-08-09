package io.xiaowei.user.service;

import io.xiaowei.model.UserModel;
import io.xiaowei.user.req.UserRegisterReq;

/**
 * @author wangxiaowei
 * @apiNote Service
 **/
public interface IUserService {

    UserModel findById(Long id);

    UserModel registerUser(UserRegisterReq userRegisterReq);
}
