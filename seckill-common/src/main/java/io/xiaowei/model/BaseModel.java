package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


/**
 * 基础model
 *
 * @author wangxiaowei
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) comment '主键ID'")
    private Long id;


    @Column(updatable = false, columnDefinition = "datetime(0) comment '创建时间'")
    @CreatedDate
    private Date createTime;


    @Column(columnDefinition = "varchar(50) comment '创建人'")
    private String createBy;


    @Column(columnDefinition = "datetime(0) comment '更新时间'")
    @LastModifiedDate
    private Date updateTime;


    @Column(columnDefinition = "varchar(50) comment '更新人'")
    private String updateBy;


    @Column(columnDefinition = "bit(1) comment '是否删除[未删除:0 已删除:1]'")
    private Boolean deleteFlag = false;


    @Column(columnDefinition = "varchar(255) comment '备注'")
    private String remark;


}