package com.selfstudy.modules.applet.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author Mark 2891517520@qq.com
 */
@Data
@ApiModel(value = "登录表单")
public class LoginForm {
    @ApiModelProperty(value = "账号")
    @NotBlank(message="账号不能为空")
    private String account;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

}
