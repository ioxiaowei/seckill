package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangxiaowei
 * @apiNote Model 商家
 **/
@Setter
@Getter
@Entity
@Table(name = "t_merchant")
public class MerChantModel extends BaseModel {

    @Column(columnDefinition = "varchar(100) comment  '账号'")
    private String account;
    @Column(columnDefinition = "varchar(100) comment  '加密密码'")
    private String encryptionPassword;
    @Column(columnDefinition = "varchar(100) comment  '原密码'")
    private String originalPassword;
    @Column(columnDefinition = "varchar(100) comment  '姓名'")
    private String name;
    @Column(columnDefinition = "varchar(100) comment  '省份'")
    private String province;
    @Column(columnDefinition = "varchar(100) comment  '城市'")
    private String city;
    @Column(columnDefinition = "varchar(100) comment  '地址'")
    private String address;
    @Column(columnDefinition = "varchar(100) comment  '手机号'")
    private String telephone;

}
