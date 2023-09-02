package com.selfstudy.modules.applet.service.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.selfstudy.common.exception.RRException;
import com.selfstudy.common.utils.R;
import com.selfstudy.common.validator.Assert;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.applet.dao.UserDao;
import com.selfstudy.modules.applet.entity.TbTokenEntity;
import com.selfstudy.modules.applet.entity.UserEntity;
import com.selfstudy.modules.applet.form.LoginForm;
import com.selfstudy.modules.applet.form.WXLoginForm;
import com.selfstudy.modules.applet.service.TbTokenService;
import com.selfstudy.modules.applet.service.UserService;
import com.selfstudy.modules.applet.utils.JwtUtils;
import com.selfstudy.modules.applet.vo.WXLoginVO;
import com.selfstudy.modules.sys.oauth2.TokenGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("userService")
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

	@Resource
	private WxMaService wxMaService;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private TbTokenService tbTokenService;
	@Autowired
	private MessageProperties messageProperties;

	@Override
	public UserEntity queryByAccount(String account) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("account", account));
	}

	@Override
	public R login(LoginForm form) {
		UserEntity user = queryByAccount(form.getAccount());
		Assert.isNull(user, "账号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException(messageProperties.getUserAccountExist(), HttpStatus.UNAUTHORIZED.value());
		}

		//生成token
		String token = jwtUtils.generateToken(user.getUserId());
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("expire", jwtUtils.getExpire());


		TbTokenEntity tbTokenEntity = new TbTokenEntity();
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + jwtUtils.getExpire() * 1000);
		tbTokenEntity.setToken(token);
		tbTokenEntity.setUserId(user.getUserId());
		tbTokenEntity.setUpdateTime(now);
		tbTokenEntity.setExpireTime(expireTime);
		tbTokenService.saveOrUpdate(tbTokenEntity);

		return R.ok(map);
	}

	@Override
	public UserEntity queryUser(Long userId) {
		return baseMapper.selectById(userId);
	}

	@Override
	public R logout(Long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		//修改token
		TbTokenEntity tokenEntity = new TbTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		boolean update = tbTokenService.updateById(tokenEntity);
		if (update){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 微信小程序登录
	 * @param wxLoginForm
	 * @return
	 */
	@Override
	public R wxLogin(WXLoginForm wxLoginForm) {
		String openid = getOpenId(wxLoginForm.getCode());
		WXLoginVO wx = this.baseMapper.selectByOpenId(openid);
		Long userId;
		if (wx == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUsername(wxLoginForm.getNickName());
			userEntity.setCreateTime(new Date());
			userEntity.setUserImg(wxLoginForm.getAvatarUrl());
			userEntity.setOpenId(openid);
			this.baseMapper.insert(userEntity);
			userId = userEntity.getUserId();
		} else {
			if (wx.getStatus() == 0) {
				throw new RRException("账号已被封禁", 401);
			}
			userId = wx.getUserId();
		}
		//生成token
		String token = jwtUtils.generateToken(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("expire", jwtUtils.getExpire());
		TbTokenEntity tbTokenEntity = new TbTokenEntity();
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + jwtUtils.getExpire() * 1000);
		tbTokenEntity.setToken(token);
		tbTokenEntity.setUserId(userId);
		tbTokenEntity.setUpdateTime(now);
		tbTokenEntity.setExpireTime(expireTime);
		tbTokenService.saveOrUpdate(tbTokenEntity);
		return R.ok(map);
	}

	/**
	 * 获取用户唯一标识
	 * @param code
	 * @return
	 */
	@Autowired
	private WxMaConfig wxMaConfig;
	private String getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		HashMap map = new HashMap();
		map.put("appid", wxMaConfig.getAppid());
		map.put("secret", wxMaConfig.getSecret());
		map.put("js_code", code);
		map.put("grant_type", "authorization_code");
		String response = HttpUtil.post(url, map);
		JSONObject jsonObject = JSON.parseObject(response);
		Object flag = jsonObject.get("openid");
		if(flag == null) {
			throw new RRException("登录超时",401);
		}
		return flag.toString();
	}

}
