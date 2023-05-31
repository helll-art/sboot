package com.ppp.sboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.entity.Elder;
import com.ppp.sboot.mapper.ElderMapper;
import com.ppp.sboot.service.IElderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
@Service
public class ElderServiceImpl extends ServiceImpl<ElderMapper, Elder> implements IElderService {

    @Resource
    private ElderMapper elderMapper;
    @Override
    public Page<Elder> findPageByDepId(Page<Elder> page, String depId) {
        return elderMapper.findPageByDepId(page , depId);
    }
}
