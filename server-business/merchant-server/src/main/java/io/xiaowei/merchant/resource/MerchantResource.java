package io.xiaowei.merchant.resource;

import io.xiaowei.model.MerChantModel;
import io.xiaowei.merchant.req.MerchantCheckInReq;
import io.xiaowei.merchant.service.IMerchantService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("merchant")
public class MerchantResource {

    @Resource
    private IMerchantService iMerchantService;

    @GetMapping("find/{id}")
    public MerChantModel findMerchantById(@PathVariable(value = "id") Long id) {
        return iMerchantService.findMerchantById(id);
    }

    @PostMapping(value = "register")
    public MerChantModel registerMerchant(@RequestBody MerchantCheckInReq merchantCheckInReq) {
        return iMerchantService.registerMerchant(merchantCheckInReq);
    }

}
