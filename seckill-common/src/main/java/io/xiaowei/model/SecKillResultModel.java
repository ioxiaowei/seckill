package io.xiaowei.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wangxiaowei
 * @apiNote Model
 **/
@Setter
@Getter
@Entity
@Table(name = "t_sec_kill_result")
public class SecKillResultModel extends BaseModel {

    //@Column(columnDefinition = "bigint(20) comment '用户ID'")
    //private Long userId;
    //@Column(columnDefinition = "bigint(20) comment '产品ID'")
    //private Long productId;
    //@Column(columnDefinition = "bigint(20) comment '秒杀ID'")
    //private Long secKillId;
    //@Column(columnDefinition = "int(20) comment '秒杀结果[0失败1成功]'")
    //private Integer result;
    //@Column(columnDefinition = "varchar(50) comment '秒杀数据'")
    //private String resultData;
    //@Column(columnDefinition = "datetime(20) comment '秒杀时间'")
    //private Date secKillTime;

}
