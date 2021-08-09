package io.xiaowei.seckill.openfeign;

import io.xiaowei.model.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote OpenFeign
 **/

@FeignClient(value = "product-server/product")
public interface ProductFeign {

    @PostMapping("find/{id}")
    ProductModel findById(@PathVariable(value = "id") Long id);

}