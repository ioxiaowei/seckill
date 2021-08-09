package io.xiaowei.shop.service;

import io.xiaowei.model.ShopModel;
import io.xiaowei.shop.req.ShopApplyReq;
import io.xiaowei.shop.req.ShopSearchReq;

import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Service
 **/
public interface IShopService {
    ShopModel applyShop(ShopApplyReq shopApplyReq);

    List<ShopModel> searchShop(ShopSearchReq shopSearchReq);
}
