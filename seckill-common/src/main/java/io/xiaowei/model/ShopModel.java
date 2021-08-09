package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangxiaowei
 * @apiNote Model 店铺
 **/
@Setter
@Getter
@Entity
@Table(name = "t_shop")
public class ShopModel extends BaseModel {

    @Column(columnDefinition = "bigint(20) comment '商铺ID'")
    private Long merchantId;

    @Column(columnDefinition = "varchar(100) comment '店铺名称'")
    private String shopName;

    @Column(columnDefinition = "varchar(100) comment '店铺描述'")
    private String shopDescription;

    @Column(columnDefinition = "varchar(100) comment '营业范围'")
    private String shopBusinessScope;

    @Column(columnDefinition = "varchar(100) comment '省份'")
    private String province;

    @Column(columnDefinition = "varchar(100) comment '城市'")
    private String city;

    @Column(columnDefinition = "varchar(100) comment '营业执照'")
    private String businessLicense;

    @Column(columnDefinition = "int(20) comment '0申请中 1申请通过 2退回 3商铺下架'")
    private Integer state;
}
