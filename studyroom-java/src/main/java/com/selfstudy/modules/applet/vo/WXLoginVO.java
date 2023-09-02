package com.selfstudy.modules.applet.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class WXLoginVO {
    @JsonSerialize(using = ToStringSerializer.class)
    Long userId;
    Integer status;
}
