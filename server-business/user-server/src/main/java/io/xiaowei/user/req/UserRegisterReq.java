package io.xiaowei.user.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangxiaowei
 * @apiNote Req
 **/
@Setter
@Getter
public class UserRegisterReq {
    private String account;
    private String password;
    private String rePassword;
    private String telephone;
    private String birthday;
    private String qq;
    private String wechat;
}
