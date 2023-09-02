package com.selfstudy.modules.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.applet.dao.TbTokenDao;
import com.selfstudy.modules.applet.entity.TbTokenEntity;
import com.selfstudy.modules.applet.service.TbTokenService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tbTokenService")
public class TbTokenServiceImpl extends ServiceImpl<TbTokenDao, TbTokenEntity> implements TbTokenService {

    @Override
    public TbTokenEntity queryByToken(String token) {
        return baseMapper.queryByToken(token);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbTokenEntity> page = this.page(
                new Query<TbTokenEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}