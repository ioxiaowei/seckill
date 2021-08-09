package io.xiaowei.product.jpa;

import io.xiaowei.model.ProductDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface ProductDetailJpa extends JpaRepository<ProductDetailModel, Long> {
}
