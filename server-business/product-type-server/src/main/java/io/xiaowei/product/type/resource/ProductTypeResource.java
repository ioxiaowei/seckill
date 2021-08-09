package io.xiaowei.product.type.resource;

import io.xiaowei.model.ProductTypeModel;
import io.xiaowei.product.type.service.IProductTypeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("product/type")
public class ProductTypeResource {

    @Resource
    private IProductTypeService iProductTypeService;

    @RequestMapping("save")
    public ProductTypeModel saveProductType(@RequestBody ProductTypeModel productTypeModel) {
        return iProductTypeService.saveProductType(productTypeModel);
    }

}
