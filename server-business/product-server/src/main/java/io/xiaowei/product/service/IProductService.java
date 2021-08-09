package io.xiaowei.product.service;


import io.xiaowei.model.ProductModel;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Service
 **/
public interface IProductService {
    ProductModel save(ProductModel productModel);

    List<ProductModel> all();

    List<ProductModel> shopList(Long shopId);

    ProductModel findById(Long id);
}
