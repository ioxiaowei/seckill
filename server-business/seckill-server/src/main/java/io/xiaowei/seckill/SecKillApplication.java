package io.xiaowei.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangxiaowei
 * @apiNote App
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan(value = {"io.xiaowei.model"})
public class SecKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecKillApplication.class);
    }
}
