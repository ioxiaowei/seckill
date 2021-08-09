package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangxiaowei
 * @apiNote Model 用户
 **/
@Setter
@Getter
@Entity
@Table(name = "t_user")
public class UserModel extends BaseModel {

    @Column(columnDefinition = "varchar(100) comment '用户名'")
    private String account;

    @Column(columnDefinition = "varchar(100) comment '加密密码'")
    private String encryptionPassword;

    @Column(columnDefinition = "varchar(100) comment '未加密密码'")
    private String originalPassword;

    @Column(columnDefinition = "int(20) comment '0男1女'")
    private Integer sex;

    @Column(columnDefinition = "varchar(100) comment '手机号'")
    private String telephone;

    @Column(columnDefinition = "varchar(100) comment ' 生日'")
    private String birthday;

    @Column(columnDefinition = "varchar(100) comment 'QQ'")
    private String qq;

    @Column(columnDefinition = "varchar(20) comment '微信号'")
    private String wechat;

    @Column(columnDefinition = "varchar(20) comment '身份证号'")
    private String idCard;

    @Column(columnDefinition = "varchar(20) comment '姓名'")
    private String name;

    @Column(columnDefinition = "int(50) comment '年龄'")
    private Integer age;

}
