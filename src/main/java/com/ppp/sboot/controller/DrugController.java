package com.ppp.sboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.common.Result;
import com.ppp.sboot.entity.Elder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.ppp.sboot.service.IDrugService;
import com.ppp.sboot.entity.Drug;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/drug")
public class DrugController {



    @Resource
    private IDrugService drugService;

    @PostMapping
    public boolean save(@RequestBody Drug drug){
        return drugService.saveOrUpdate(drug);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return drugService.removeById(id);
    }
    @GetMapping
    public List<Drug> findAll(){
        return drugService.list();
    }
    @GetMapping("/{id}")
    public Drug findOne(@PathVariable Integer id){
        return drugService.getById(id);
    }
    @PostMapping("/shop/{userId}/{drugId}")
    public Result shopByUserId(@PathVariable String userId, @PathVariable String drugId){
        System.out.println("=================================elder/page/==================================");
        System.out.println(userId);
        System.out.println("=================================elder/page/==================================");
        boolean res1 = drugService.shopByUserId(userId,drugId);
        System.out.println(res1);
        return Result.success();
    }
    @GetMapping("/page")
    public Page<Drug> findPage(@RequestParam Integer pageNum ,
                                    @RequestParam Integer pageSize){
        QueryWrapper<Drug>   queryWrapper = new QueryWrapper<>();
        return drugService.page(new Page<>(pageNum , pageSize) );
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return drugService.removeByIds(ids);
    }
}
