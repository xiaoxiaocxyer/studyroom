package com.selfstudy.modules.applet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户
 *
 * @author Mark 2891517520@qq.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * '状态  0：禁用   1：正常'
	 */
	private Integer status;
	/**
	 * '姓名'
	 */
	private String name;
	/**
	 * 'qq账号'
	 */
	private Integer qq;

	/**
	 * '邮箱'
	 */
	private String email;
	/**
	 * '备注'
	 */
	private String bz;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 用户头像
	 */
	private String userImg;

	/**
	 * openId
	 */
	private String openId;

}
