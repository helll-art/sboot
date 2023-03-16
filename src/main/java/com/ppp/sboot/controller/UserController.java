package com.ppp.sboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.entity.User;
import com.ppp.sboot.mapper.UserMapper;
import com.ppp.sboot.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @PostMapping
    public boolean save(@RequestBody User user){
        //新增或者更新
        return userService.saveUser(user);
    }
    @GetMapping("/")
    public List<User> findAll(){
        return userService.list();
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){return userService.removeByIds(ids);}
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum ,
                                @RequestParam Integer pageSize ,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String address) {
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
}
