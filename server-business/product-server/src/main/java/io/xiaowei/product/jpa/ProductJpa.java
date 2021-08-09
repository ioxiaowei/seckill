package io.xiaowei.product.jpa;

import io.xiaowei.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface ProductJpa extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByStateAndShopId(Integer state, Long shopId);

}
