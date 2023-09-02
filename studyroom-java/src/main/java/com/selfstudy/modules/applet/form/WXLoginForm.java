package com.selfstudy.modules.applet.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 微信登录表单对象
 * @author jingjiu
 * @date 2023/2/2 15:04
 */
@Data
@ApiModel(value = "WXLoginForm", description = "微信登录表单对象")
public class WXLoginForm {

    /**
     * 登录凭证
     */
    @NotBlank(message = "登录凭证不能为空")
    @ApiModelProperty(value = "登录凭证", required = true)
    private String code;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatarUrl;

}
