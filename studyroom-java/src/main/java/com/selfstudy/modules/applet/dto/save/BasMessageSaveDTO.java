package com.selfstudy.modules.applet.dto.save;

import lombok.Data;

@Data
public class BasMessageSaveDTO {
    /**
     * 留言内容
     */
    private String message;
    /**
     * 0留言 1举报
     */
    private Integer messageType;
}
