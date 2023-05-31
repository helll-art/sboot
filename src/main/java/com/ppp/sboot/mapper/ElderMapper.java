package com.ppp.sboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.entity.Elder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
public interface ElderMapper extends BaseMapper<Elder> {

    Page<Elder> findPageByDepId(Page<Elder> page, String depId);
}
