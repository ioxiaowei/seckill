package io.xiaowei.merchant.service;

import io.xiaowei.model.MerChantModel;
import io.xiaowei.merchant.jpa.MerchantJpa;
import io.xiaowei.merchant.req.MerchantCheckInReq;
import io.xiaowei.merchant.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
@Slf4j
public class MerchantServiceImpl implements IMerchantService {
    @Resource
    private MerchantJpa merchantJpa;

    @Override
    public MerChantModel findMerchantById(Long id) {
        return merchantJpa.findById(id).orElse(null);
    }

    @Override
    public MerChantModel registerMerchant(MerchantCheckInReq merchantCheckInReq) {
        if (StringUtils.isBlank(merchantCheckInReq.getAccount())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(merchantCheckInReq.getPassword()) || StringUtils.isBlank(merchantCheckInReq.getRePassword())) {
            throw new RuntimeException("密码不能为空");
        }
        if (!merchantCheckInReq.getPassword().equals(merchantCheckInReq.getRePassword())) {
            throw new RuntimeException("两次密码输入不一致");
        }
        MerChantModel merChantModel = new MerChantModel();
        merChantModel.setAccount(merchantCheckInReq.getAccount());
        merChantModel.setTelephone(merchantCheckInReq.getTelephone());
        merChantModel.setAddress(merchantCheckInReq.getAddress());
        merChantModel.setCity(merchantCheckInReq.getCity());
        merChantModel.setProvince(merchantCheckInReq.getProvince());
        merChantModel.setName(merchantCheckInReq.getName());
        String password = merchantCheckInReq.getPassword();
        merChantModel.setOriginalPassword(password);
        try {
            merChantModel.setEncryptionPassword(Md5Util.md5(password, Md5Util.MD5KEY));
        } catch (Exception exception) {
            throw new RuntimeException("加密失败");
        }
        return merchantJpa.saveAndFlush(merChantModel);
    }
}
