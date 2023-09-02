package com.selfstudy.modules.applet.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 我的预约信息返回模型
 */
@Data
public class BasAppointmentVO {
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 座位id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long seatId;
    /**
     * 预约者id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
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
     * 审核状态
     */
    private Integer seatState;
    /**
     * 预约时间区间
     */
    private String seatDay;



    /**
     * 座位名称
     */
    private String sName;
    /**
     * 自习室名称
     */
    private String roomName;
    /**
     * 楼层
     */
    private String floor;
}
