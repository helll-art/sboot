package com.ppp.sboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.entity.Elder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
public interface IElderService extends IService<Elder> {

    Page<Elder> findPageByDepId(Page<Elder> page, String depId);
}
