package com.ppp.sboot.mapper;

import com.ppp.sboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
