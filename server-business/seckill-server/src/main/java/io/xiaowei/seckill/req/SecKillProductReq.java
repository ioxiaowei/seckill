package io.xiaowei.seckill.req;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxiaowei
 * @apiNote Req
 **/
@Setter
@Getter
public class SecKillProductReq {

    private Long productId;

    private Long secKillInventory;

    private BigDecimal secKillPrice;

    private Date startTime;

    private Date endTime;

    private String productTitle;

}
