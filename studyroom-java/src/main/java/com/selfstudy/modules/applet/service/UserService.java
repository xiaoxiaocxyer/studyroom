package com.selfstudy.modules.applet.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.common.utils.R;
import com.selfstudy.modules.applet.entity.UserEntity;
import com.selfstudy.modules.applet.form.LoginForm;
import com.selfstudy.modules.applet.form.WXLoginForm;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 用户
 *
 * @author Mark 2891517520@qq.com
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByAccount(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	R login(LoginForm form);

	UserEntity queryUser(Long userId);

	R logout(Long userId);

	/**
	 * 微信小程序登录
	 * @param wxLoginForm
	 * @return
	 */
	R wxLogin(WXLoginForm wxLoginForm);

}
