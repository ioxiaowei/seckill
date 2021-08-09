package io.xiaowei.merchant.jpa;

import io.xiaowei.model.MerChantModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangxiaowei
 * @apiNote Jpa
 **/
public interface MerchantJpa extends JpaRepository<MerChantModel, Long> {

}
