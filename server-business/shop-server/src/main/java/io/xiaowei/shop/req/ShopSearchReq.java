package io.xiaowei.shop.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangxiaowei
 * @apiNote Req
 **/
@Setter
@Getter
public class ShopSearchReq {
    private String shopName;
    private Integer state;
}
