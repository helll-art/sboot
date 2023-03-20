package com.ppp.sboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
    public Page<Elder> findPage(@RequestParam Integer pageNum ,
                                    @RequestParam Integer pageSize){
        QueryWrapper<Elder>   queryWrapper = new QueryWrapper<>();
        return elderService.page(new Page<>(pageNum , pageSize) );
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return elderService.removeByIds(ids);
    }
}
