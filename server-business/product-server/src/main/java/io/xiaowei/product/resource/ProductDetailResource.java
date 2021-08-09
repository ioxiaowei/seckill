package io.xiaowei.product.resource;

import io.xiaowei.product.service.IProductDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("product/detail")
public class ProductDetailResource {

    @Resource
    private IProductDetailService iProductDetailService;

}
