package com.ppp.sboot.service.impl;

import com.ppp.sboot.entity.Drug;
import com.ppp.sboot.mapper.DrugMapper;
import com.ppp.sboot.mapper.ElderMapper;
import com.ppp.sboot.service.IDrugService;
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
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

    @Resource
    private DrugMapper drugMapper;
    @Override
    public boolean shopByUserId(String userId,String drugId) {
        boolean res1 = drugMapper.changeSynHealthy(userId);
        boolean res2 = drugMapper.changeInventory(drugId);
        boolean res3 = drugMapper.changeCost(userId,drugId);
        return drugMapper.shopByUserId(userId);
    }
}
