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
@Table(name = "t_product_type")
public class ProductTypeModel extends BaseModel {

    @Column(columnDefinition = "varchar(100) comment '产品类型名称'")
    private String productTypeName;
    @Column(columnDefinition = "varchar(100) comment '产品类型描述'")
    private String productTypeDescription;
    @Column(columnDefinition = "varchar(100) comment '产品父级产品'")
    private Long parentId;
    @Column(columnDefinition = "varchar(100) comment '产品级别'")
    private Integer grade;

}
