package io.xiaowei.product.type.service;

import io.xiaowei.model.ProductTypeModel;
import io.xiaowei.product.type.jpa.ProductTypeJpa;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Resource
    private ProductTypeJpa productTypeJpa;


    @Override
    public ProductTypeModel saveProductType(ProductTypeModel productTypeModel) {
        return productTypeJpa.saveAndFlush(productTypeModel);
    }
}
