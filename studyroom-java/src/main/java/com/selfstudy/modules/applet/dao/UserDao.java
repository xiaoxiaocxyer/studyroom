package com.selfstudy.modules.applet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.applet.entity.UserEntity;
import com.selfstudy.modules.applet.vo.WXLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 *
 * @author Mark 2891517520@qq.com
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 根据openId查询用户ID
     * @param openid
     * @return
     */
    @Select("select user_id,`status` from tb_user where open_id = #{openid}")
    WXLoginVO selectByOpenId(@Param("openid") String openid);

}
