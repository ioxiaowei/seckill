package io.xiaowei.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangxiaowei
 * @apiNote App
 **/
@EntityScan(value = {"io.xiaowei.model"})
@SpringBootApplication
@EnableEurekaClient
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class);
    }
}
