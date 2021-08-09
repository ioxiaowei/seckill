package io.xiaowei.shop.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangxiaowei
 * @apiNote Req
 **/
@Setter
@Getter
public class ShopApplyReq {
    private Long merchantId;
    private String shopName;
    private String shopDescription;
    private String shopBusinessScope;
    private String province;
    private String city;
    private String businessLicense;
}
