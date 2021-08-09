package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangxiaowei
 * @apiNote Model
 **/
@Setter
@Getter
@Entity
@Table(name = "t_product_detail")
public class ProductDetailModel extends BaseModel {

    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;
    @Column(columnDefinition = "varchar(100) comment '产地'")
    private String productPlace;
    @Column(columnDefinition = "varchar(100) comment '产品品牌'")
    private String productBrand;
    @Column(columnDefinition = "varchar(100) comment '产品描述'")
    private String productDescription;
    @Column(columnDefinition = "varchar(100) comment '产品重量'")
    private String productWeight;
    @Column(columnDefinition = "varchar(100) comment '产品详细图片'")
    private String productDetailPictureUrl;
    @Column(columnDefinition = "varchar(100) comment '规格及包装'")
    private String specificationAndPack;

}
