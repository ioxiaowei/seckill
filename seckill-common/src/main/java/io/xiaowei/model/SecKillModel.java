package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxiaowei
 * @apiNote Model
 **/
@Setter
@Getter
@Entity
@Table(name = "t_sec_kill")
public class SecKillModel extends BaseModel {

    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;
    @Column(columnDefinition = "int(100) comment '秒杀数量'")
    private Integer secKillNum;
    @Column(columnDefinition = "decimal(10,2) comment '秒杀价格'")
    private BigDecimal secKillPrice;
    @Column(columnDefinition = "bigint(20) comment '秒杀库存'")
    private Long secKillInventory;
    @Column(columnDefinition = "timestamp(0) comment '秒杀开始时间'")
    private Date startTime;
    @Column(columnDefinition = "timestamp(0) comment '秒杀结束时间'")
    private Date endTime;
    @Column(columnDefinition = "bigint(20) comment '店铺ID'")
    private Long shopId;
    @Column(columnDefinition = "int(20) comment '审核[0待审核 1通过 2不通过]'")
    private Integer state;
    @Column(columnDefinition = "decimal(10,2) comment '产品原价'")
    private BigDecimal productPrice;
    @Column(columnDefinition = "varchar(100) comment '产品标题'")
    private String productTitle;
    @Column(columnDefinition = "varchar(100) comment '产品名称'")
    private String productName;

    @Column(columnDefinition = "timestamp(0) comment '批准时间'")
    private Date approveTime;
    //
    //private int seckillversion;

}
