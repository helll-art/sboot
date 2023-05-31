package com.ppp.sboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.common.Result;
import com.ppp.sboot.entity.Company;
import com.ppp.sboot.entity.User;
import com.ppp.sboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.ppp.sboot.service.IElderService;
import com.ppp.sboot.entity.Elder;

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
@RequestMapping("/elder")
public class ElderController {



    @Resource
    private IElderService elderService;

    @PostMapping
    public boolean save(@RequestBody Elder elder){
        return elderService.saveOrUpdate(elder);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return elderService.removeById(id);
    }
    @GetMapping
    public List<Elder> findAll(){
        return elderService.list();
    }
    @GetMapping("/{id}")
    public Elder findOne(@PathVariable Integer id){
        return elderService.getById(id);
    }
    @GetMapping("/page")
    public Result findPage(
            @RequestParam Integer pageNum ,
                                    @RequestParam Integer pageSize
            ){
        System.out.println("=================================elder/page==================================");

        System.out.println("=================================elder/page==================================");
        IPage<Elder> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Elder>   queryWrapper = new QueryWrapper<>();
        return Result.success(elderService.page(page,queryWrapper));
    }
    @GetMapping("/page/{DepId}")
    public Result findPageByDepId(@PathVariable String DepId,
            @RequestParam Integer pageNum ,
            @RequestParam Integer pageSize
    ){
        System.out.println("=================================elder/page/==================================");
        System.out.println(DepId);
        System.out.println("=================================elder/page/==================================");
//        IPage<Elder> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<Elder>   queryWrapper = new QueryWrapper<>();
//        return Result.success(elderService.page(page,queryWrapper));
        //通过Dep_Eld表查询出符合条件的elder
        Page<Elder> page = elderService.findPageByDepId(new Page<>(pageNum , pageSize),DepId);
        return Result.success(page);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return elderService.removeByIds(ids);
    }
}
