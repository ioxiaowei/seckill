package io.xiaowei.merchant.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangxiaowei
 * @apiNote Req
 **/
@Setter
@Getter
public class MerchantCheckInReq {
    private String account;
    private String password;
    private String rePassword;
    private String telephone;
    private String province;
    private String name;
    private String city;
    private String address;
}
