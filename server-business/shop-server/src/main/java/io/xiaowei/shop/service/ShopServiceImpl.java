package io.xiaowei.shop.service;

import io.xiaowei.model.ShopModel;
import io.xiaowei.shop.jpa.ShopJpa;
import io.xiaowei.shop.req.ShopApplyReq;
import io.xiaowei.shop.req.ShopSearchReq;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
public class ShopServiceImpl implements IShopService {

    @Resource
    private ShopJpa shopJpa;

    @Override
    public ShopModel applyShop(ShopApplyReq shopApplyReq) {
        if (StringUtils.isBlank(shopApplyReq.getShopName())) {
            throw new RuntimeException("店铺名称不能为空");
        }
        if (StringUtils.isBlank(shopApplyReq.getShopBusinessScope())) {
            throw new RuntimeException("店铺经营范围不能为空");
        }
        if (StringUtils.isBlank(shopApplyReq.getBusinessLicense())) {
            throw new RuntimeException("店铺营业执照不能为空");
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setMerchantId(shopApplyReq.getMerchantId());
        shopModel.setProvince(shopApplyReq.getProvince());
        shopModel.setCity(shopApplyReq.getCity());
        shopModel.setBusinessLicense(shopApplyReq.getBusinessLicense());
        shopModel.setShopName(shopApplyReq.getShopName());
        shopModel.setShopDescription(shopApplyReq.getShopDescription());
        shopModel.setShopBusinessScope(shopApplyReq.getShopBusinessScope());
        shopModel.setState(0);
        return shopJpa.saveAndFlush(shopModel);
    }

    @Override
    public List<ShopModel> searchShop(ShopSearchReq shopSearchReq) {
        return shopJpa.findByShopNameLikeAndState(shopSearchReq.getShopName(), shopSearchReq.getState());
    }
}
