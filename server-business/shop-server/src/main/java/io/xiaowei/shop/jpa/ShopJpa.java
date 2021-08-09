package io.xiaowei.shop.jpa;

import io.xiaowei.model.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface ShopJpa extends JpaRepository<ShopModel, Long> {

    List<ShopModel> findByShopNameLikeAndState(String shopName, Integer state);

}
