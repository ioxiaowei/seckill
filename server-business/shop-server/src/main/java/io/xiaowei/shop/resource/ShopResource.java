package io.xiaowei.shop.resource;

import io.xiaowei.model.ShopModel;
import io.xiaowei.shop.req.ShopApplyReq;
import io.xiaowei.shop.req.ShopSearchReq;
import io.xiaowei.shop.service.IShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("shop")
public class ShopResource {

    @Resource
    private IShopService iShopService;

    @PostMapping("apply")
    public ShopModel applyShop(@RequestBody ShopApplyReq shopApplyReq) {
        return iShopService.applyShop(shopApplyReq);
    }

    @PostMapping("search")
    public List<ShopModel> searchShop(@RequestBody ShopSearchReq shopSearchReq) {
        return iShopService.searchShop(shopSearchReq);
    }
}
