package com.selfstudy.modules.applet.dto.save;

import lombok.Data;

@Data
public class BasAppointmentSaveDTO {
    /**
     * 座位id
     */
    private Long seatId;
    /**
     * 预约者电话
     */
    private String seatPhone;
    /**
     * 预约者姓名
     */
    private String seatName;
    /**
     * 预约者专业班级
     */
    private String seatClass;
    /**
     * 预约时间区间
     */
    private String seatDay;
}
