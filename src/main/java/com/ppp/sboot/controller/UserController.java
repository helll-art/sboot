package com.ppp.sboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppp.sboot.common.Constants;
import com.ppp.sboot.common.Result;
import com.ppp.sboot.controller.dto.UserDTO;
import com.ppp.sboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.ppp.sboot.service.IUserService;
import com.ppp.sboot.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/Login")
    public Result login(@RequestBody UserDTO userDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){ // 字符串是不是空
            return Result.error(Constants.Code_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }
    @PostMapping("/Register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        System.out.println(userDTO.getUsername());
        String password = userDTO.getPassword();
        System.out.println(userDTO.getPassword());
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){ // 字符串是不是空
            return Result.error(Constants.Code_400,"参数错误");
        }
        System.out.println("UserController====register");
        return Result.success(userService.register(userDTO));
    }
    @PostMapping
    public Result save(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));

    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        return Result.success(userService.removeById(id));

    }
    @GetMapping
//    public List<User> findAll(){
//        return userService.list();
//    }
    public Result findAll() {
        return Result.success(userService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(userService.getById(id));

    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum ,
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

        //获取当前用户信息
        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("UserController ======================================================" + currentUser.getNickname());
        return Result.success(userService.page(page,queryWrapper));
//        return Result.success(userService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }



    @GetMapping("/export")
    public void export(HttpServletResponse response ) throws Exception{
        //从数据库张查询出所有数据
        List<User> list = userService.list();
        //通过工具类创建writer写出到磁盘路径
        //ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
       // writer.addHeaderAlias("avatarUrl","头像");
        writer.addHeaderAlias("userstatus","用户状态");
        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");

        //获取输出流
        ServletOutputStream out = response.getOutputStream();
        //把Excel writer中的东西刷新到输出流中
        writer.flush(out,true);
        out.close();
        writer.close();
    }



    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //通过javaBean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        //List<User> list = reader.readAll(User.class);


        //忽略表头的中文,直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for(List<Object> row : list){
            User user = new User();
            user.setUsername(row.get(1).toString());
            user.setPassword(row.get(2).toString());
            user.setNickname(row.get(3).toString());
            user.setEmail(row.get(4).toString());
            user.setPhone(row.get(5).toString());
            user.setAddress(row.get(6).toString());

            user.setUserstatus(row.get(8).toString());
            users.add(user);

        }
        userService.saveBatch(users);
        return Result.success(true) ;
//        List<User> list = reader.read(0,1, User.class);
//        userService.saveBatch(list);
//        return true;
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        System.out.println(ids);
        return Result.success(userService.removeByIds(ids));

    }
}
