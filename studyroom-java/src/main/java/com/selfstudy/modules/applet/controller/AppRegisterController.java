package com.selfstudy.modules.applet.controller;


import com.selfstudy.common.utils.R;
import com.selfstudy.common.validator.ValidatorUtils;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.applet.entity.UserEntity;
import com.selfstudy.modules.applet.form.RegisterForm;
import com.selfstudy.modules.applet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * 注册
 *
 * @author Mark 2891517520@qq.com
 */
@RestController
@RequestMapping("/applet")
@Api(tags = "小程序注册接口")
public class AppRegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageProperties messageProperties;

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        UserEntity user = new UserEntity();
        user.setAccount(form.getAccount());
        user.setUsername("用户_"+UUID.randomUUID().toString().substring(0,8));
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        boolean save = userService.save(user);
        if (save){
            return R.ok();
        }

        return R.error(messageProperties.getFormSaveError());
    }
}
