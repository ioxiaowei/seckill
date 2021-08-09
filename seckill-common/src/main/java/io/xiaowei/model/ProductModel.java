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
@Table(name = "t_product")
public class ProductModel extends BaseModel {
    @Column(columnDefinition = "varchar(100) comment '商品标题'")
    private String productTitle;
    @Column(columnDefinition = "varchar(100) comment '商品名称'")
    private String productName;
    @Column(columnDefinition = "varchar(100) comment '图片地址'")
    private String productPictureUrl;
    @Column(columnDefinition = "decimal(10,2) comment '原价'")
    private BigDecimal productPrice;
    @Column(columnDefinition = "decimal(10,2) comment '优惠价格'")
    private BigDecimal productDiscounts;
    @Column(columnDefinition = "int(20) comment '商品状态[0申请中1审核通过2退回3上架4下架]'")
    private Integer state;
    @Column(columnDefinition = "timestamp(0) comment '审核时间'")
    private Date approveTime;
    @Column(columnDefinition = "bigint(20) comment '商户id'")
    private Long merchantId;
    @Column(columnDefinition = "bigint(20) comment '商品类别id'")
    private Long productTypeId;
    @Column(columnDefinition = "bigint(20) comment '库存'")
    private Long productInventory;
    @Column(columnDefinition = "bigint(20) comment '商铺id'")
    private Long shopId;

}
