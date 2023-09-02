package com.selfstudy.modules.applet.controller;


import com.selfstudy.common.utils.R;
import com.selfstudy.common.validator.ValidatorUtils;
import com.selfstudy.modules.applet.annotation.Login;
import com.selfstudy.modules.applet.form.LoginForm;
import com.selfstudy.modules.applet.form.WXLoginForm;
import com.selfstudy.modules.applet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 小程序登录接口
 *
 * @author Mark 2891517520@qq.com
 */
@Validated
@RestController
@RequestMapping("/applet")
@Api(tags = "小程序登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;

    /**
     * 微信小程序登录
     * @param wxLoginForm
     * @return
     */
    @PostMapping("/wxLogin")
    @ApiOperation("微信小程序登录")
    public R wxLogin(@RequestBody @Validated WXLoginForm wxLoginForm) {
        return R.ok(userService.wxLogin(wxLoginForm));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        //用户登录
        Map<String, Object> map = userService.login(form);
        return R.ok(map);
    }

    @Login
    @PostMapping("/logOut")
    @ApiOperation("退出登录 不需要传参")
    public R logout(@RequestAttribute("userId") Long userId){
        return userService.logout(userId);
    }
}
