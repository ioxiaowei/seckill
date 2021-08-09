package io.xiaowei.merchant.service;

import io.xiaowei.model.MerChantModel;
import io.xiaowei.merchant.req.MerchantCheckInReq;

/**
 * @author wangxiaowei
 * @apiNote Service
 **/
public interface IMerchantService {

    MerChantModel findMerchantById(Long id);

    MerChantModel registerMerchant(MerchantCheckInReq merchantCheckInReq);

}
