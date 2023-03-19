package com.ppp.sboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.ppp.sboot.service.IUserService;
import com.ppp.sboot.entity.User;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/user")
public class UserController {



    @Resource
    private IUserService userService;

    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }
    @GetMapping
    public List<User> findAll(){
        return userService.list();
    }
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id){
        return userService.getById(id);
    }
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum ,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User>   queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }

        //queryWrapper.or().like("address",address);
        return userService.page(page,queryWrapper);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeByIds(ids);
    }
}
