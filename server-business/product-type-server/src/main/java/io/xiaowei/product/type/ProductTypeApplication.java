package io.xiaowei.product.type;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangxiaowei
 * @apiNote App
 **/
@SpringBootApplication
@EnableEurekaClient
@EntityScan(value = {"io.xiaowei.model"})
public class ProductTypeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductTypeApplication.class);
    }
}
