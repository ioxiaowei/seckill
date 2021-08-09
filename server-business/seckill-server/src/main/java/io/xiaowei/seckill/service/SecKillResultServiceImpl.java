package io.xiaowei.seckill.service;

import io.xiaowei.model.SecKillResultModel;
import io.xiaowei.seckill.jpa.SecKillResultJpa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangxiaowei
 * @apiNote Impl
 **/
@Service
@Slf4j
public class SecKillResultServiceImpl implements ISecKillResultService {

    @Resource
    private SecKillResultJpa secKillResultJpa;

    @Override
    public SecKillResultModel save(SecKillResultModel secKillResultModel) {
        return secKillResultJpa.saveAndFlush(secKillResultModel);
    }
}
