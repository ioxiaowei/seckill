package io.xiaowei.user.service;

import io.xiaowei.model.UserModel;
import io.xiaowei.user.jpa.UserJpa;
import io.xiaowei.user.req.UserRegisterReq;
import io.xiaowei.user.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserJpa userJpa;

    @Override
    public UserModel findById(Long id) {
        return userJpa.findById(id).orElse(null);
    }

    @Override
    public UserModel registerUser(UserRegisterReq userRegisterReq) {
        if (StringUtils.isBlank(userRegisterReq.getAccount())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(userRegisterReq.getPassword()) || StringUtils.isBlank(userRegisterReq.getRePassword())) {
            throw new RuntimeException("密码不能为空");
        }
        if (!userRegisterReq.getPassword().equals(userRegisterReq.getRePassword())) {
            throw new RuntimeException("两次密码输入不一致");
        }
        UserModel userModel = new UserModel();
        userModel.setAccount(userRegisterReq.getAccount());
        userModel.setBirthday(userRegisterReq.getBirthday());
        userModel.setTelephone(userRegisterReq.getTelephone());
        userModel.setQq(userRegisterReq.getQq());
        userModel.setWechat(userRegisterReq.getWechat());
        String password = userRegisterReq.getPassword();
        userModel.setOriginalPassword(password);
        try {
            userModel.setEncryptionPassword(Md5Util.md5(password, Md5Util.MD5KEY));
        } catch (Exception exception) {
            throw new RuntimeException("MD5加密失败");
        }
        return userJpa.saveAndFlush(userModel);
    }
}
