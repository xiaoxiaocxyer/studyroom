package com.selfstudy.modules.applet.dto.update;

import lombok.Data;

/**
 * 用户信息修改传输模型
 */
@Data
public class UserInfoUpdateDTO {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;
    /**
     * qq账号
     */
    private Integer qq;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String bz;
    /**
     * 头像
     */
    private String userImg;
}
