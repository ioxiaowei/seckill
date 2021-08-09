package io.xiaowei.product.service;

import io.xiaowei.model.ProductModel;
import io.xiaowei.product.jpa.ProductJpa;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
public class ProductServiceImpl implements IProductService {

    @Resource
    private ProductJpa productJpa;

    @Override
    public ProductModel save(ProductModel productModel) {
        return productJpa.saveAndFlush(productModel);
    }

    @Override
    public List<ProductModel> all() {
        return productJpa.findAll();
    }

    /**
     * 审核通过商铺列表
     *
     * @param shopId 商铺ID
     * @return
     */
    @Override
    public List<ProductModel> shopList(Long shopId) {
        return productJpa.findByStateAndShopId(1, shopId);
    }

    @Override
    public ProductModel findById(Long id) {
        return productJpa.findById(id).orElse(null);
    }


}
