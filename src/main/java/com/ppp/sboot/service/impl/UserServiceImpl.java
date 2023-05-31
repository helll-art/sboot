package com.ppp.sboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ppp.sboot.common.Constants;
import com.ppp.sboot.common.Result;
import com.ppp.sboot.controller.dto.UserDTO;
import com.ppp.sboot.entity.Healthy;
import com.ppp.sboot.entity.Menu;
import com.ppp.sboot.entity.Tips;
import com.ppp.sboot.entity.User;
import com.ppp.sboot.exception.ServiceException;
import com.ppp.sboot.mapper.*;
import com.ppp.sboot.service.IMenuService;
import com.ppp.sboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppp.sboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Autowired
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private DepEldMapper depEldMapper;
    @Resource
   private RoleMenuMapper roleMenuMapper;

    @Resource
    private HealthyMapper healthyMapper;
    @Resource
    private IMenuService menuService;
    //    private static final log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);

            String role = one.getUserstatus(); //ROLE_ADMIN
            //设置用户菜单列表
            List<Menu> roleMenus= getRoleMenus(role);
             userDTO.setMenus(roleMenus);
             List<Integer> ids = depEldMapper.selectByDepId(one.getId());
             userDTO.setIds(ids);
             //设置消息提示
            Healthy heal = healthyMapper.getHealthyById(one.getId());
            Integer range1 = heal.getDrugHealthy();
            Integer range2 = heal.getDishHealthy();
            Integer range3 = heal.getSportHealthy();
            if(range1 < 80){
                range1 = 60;
            }else if (range1 >=80 && range1 <90){
                range1 = 80;
            }else if(range1 >= 90 && range1 <= 100){
                range1 = 90;
            }

            if(range2 < 80){
                range2 = 60;
            }else if (range2 >=80 && range2 <90){
                range2 = 80;
            }else if(range2 >= 90 && range2 <= 100){
                range2 = 90;
            }
            if(range3 < 80){
                range3 = 60;
            }else if (range3 >=80 && range3 <90){
                range3 = 80;
            }else if(range3 >= 90 && range3 <= 100){
                range3 = 90;
            }
            List<String> message = new ArrayList<>();
            message.add(healthyMapper.getDrugMessage(range1));
            message.add(healthyMapper.getDishMessage(range2));
            message.add(healthyMapper.getSportMessage(range3));
            userDTO.setTips(message);
            return userDTO;
        } else {
            throw new ServiceException(Constants.Code_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        System.out.println("UserServiceImpl====register");
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);

            save(one);//把copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.Code_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
//            e.printStackTrace();
            throw new ServiceException(Constants.Code_500, "系统错误");
        }
        return one;
    }
    //获取当前菜单角色列表
    private List<Menu>  getRoleMenus(String roleFlag){
        Integer roleId = roleMapper.selectByFlag(roleFlag);

        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        System.out.println("=====================================================menuIds==============================");
        System.out.println(menuIds);
        System.out.println("=====================================================menuIds==============================");
        //查出系统所有的菜单
        List<Menu> menus = menuService.findMenus("");
        System.out.println("=====================================================menus==============================");
        System.out.println(menus);
        System.out.println("=====================================================menus==============================");
        //new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();

        //筛选当前用户角色的菜单
        for(Menu menu : menus){
            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //removeIf()  移除children里面不在menuIds集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));

        }
        return roleMenus;
    }
}
