package io.xiaowei.product.type.jpa;

import io.xiaowei.model.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface ProductTypeJpa extends JpaRepository<ProductTypeModel, Long> {

}
