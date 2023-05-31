package com.ppp.sboot.mapper;

import com.ppp.sboot.entity.Drug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
public interface DrugMapper extends BaseMapper<Drug> {

    boolean shopByUserId(String userId);

    boolean changeSynHealthy(String userId);

    boolean changeInventory(String drugId);

    boolean changeCost(String userId,String drugId);
}
