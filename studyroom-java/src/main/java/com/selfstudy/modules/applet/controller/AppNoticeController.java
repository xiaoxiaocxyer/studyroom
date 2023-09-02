package com.selfstudy.modules.applet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.base.QueryInfoDTO;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.applet.annotation.Login;
import com.selfstudy.modules.applet.annotation.LoginUser;
import com.selfstudy.modules.applet.dto.save.BasAppointmentSaveDTO;
import com.selfstudy.modules.applet.dto.save.BasMessageSaveDTO;
import com.selfstudy.modules.applet.dto.update.UserInfoUpdateDTO;
import com.selfstudy.modules.applet.entity.UserEntity;
import com.selfstudy.modules.applet.vo.BasAppointmentVO;
import com.selfstudy.modules.bas.entity.*;
import com.selfstudy.modules.bas.service.*;
import com.selfstudy.modules.user.entity.TbUserEntity;
import com.selfstudy.modules.user.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序
 *
 * @author Mark 2891517520@qq.com
 */
@RestController
@RequestMapping("/applet")
@Api(tags = "小程序接口")
public class AppNoticeController {

    @Autowired
    private MessageProperties messageProperties;
    @Autowired
    private BasNoticeService basNoticeService;
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private BasFloorService basFloorService;
    @Autowired
    private BasStudyRoomService basStudyRoomService;
    @Autowired
    private BasSeatService basSeatService;
    @Autowired
    private BasAppointmentService basAppointmentService;
    @Autowired
    private BasMessageService basMessageService;
    /**
     * 列表
     */
    @GetMapping("/listNotice")
    @ApiOperation("公告分页列表")
    public R listNotice(QueryInfoDTO queryInfoDTO){
        PageResult<BasNoticeEntity> pageResult = basNoticeService.listNotice(queryInfoDTO);
        return R.ok().put("data", pageResult);
    }

    @ApiOperation("查看封禁用户")
    @GetMapping("/listForBan")
    public R listForBan(){
        List<TbUserEntity> voPageResult = tbUserService.listForBan();
        return R.ok().put("data", voPageResult);
    }

    @ApiOperation("获取全部楼层")
    @GetMapping("/getAllFloor")
    public R getAllFloor(){
        List<BasFloorEntity> list = basFloorService.list();
        return R.ok().put("data",list);
    }

    @ApiOperation("通过楼层获取自习室")
    @GetMapping("/getRoomByFloor")
    public R getRoomByFloor(Long id){
        List<BasStudyRoomEntity> roomEntities = basStudyRoomService.getRoomByFloor(id);
        return R.ok().put("data", roomEntities);
    }

    @ApiOperation("通过自习室获取座位")
    @GetMapping("/getSeatByRoom")
    public R getSeatByRoom(Long id){
        List<BasSeatEntity> roomEntities = basSeatService.getSeatByRoom(id);
        return R.ok().put("data", roomEntities);
    }


    @Login
    @ApiOperation("保存预约信息")
    @PostMapping("/appointment")
    public R appointment(@RequestBody BasAppointmentSaveDTO saveDTO,@RequestAttribute("userId") Long userId){
        boolean save = basAppointmentService.appointment(saveDTO,userId);
        if (save){
            return R.ok();
        }
        return R.error(messageProperties.getFormSaveError());
    }

    @Login
    @ApiOperation("我的预约信息")
    @PostMapping("/myAppointment")
    public R myAppointment(@RequestAttribute("userId") Long userId){
        List<BasAppointmentVO> basAppointment =  basAppointmentService.myAppointment(userId);
        return R.ok().put("data",basAppointment);
    }

    @Login
    @ApiOperation("取消预约")
    @PostMapping("/cancel")
    public R cancel(Long id){
        Boolean cancel = basAppointmentService.cancel(id);
        if (cancel){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }


    @Login
    @ApiOperation("结束学习")
    @PostMapping("/over")
    public R over(Long id){
        Boolean over = basAppointmentService.over(id);
        if (over){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    @Login
    @GetMapping("/getUser")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("data", user);
    }

    @Login
    @ApiOperation("用户信息修改")
    @PostMapping("/userInfo")
    public R userInfo(@RequestAttribute("userId") Long userId, @RequestBody UserInfoUpdateDTO updateDTO){
        TbUserEntity tbUserEntity = BeanUtil.copyProperties(updateDTO, TbUserEntity.class);
        tbUserEntity.setUserId(userId);
        boolean update = tbUserService.updateById(tbUserEntity);
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    @Login
    @ApiOperation("用户留言")
    @PostMapping("/userMessage")
    public R userMessage(@LoginUser UserEntity user, @RequestBody BasMessageSaveDTO updateDTO){
        BasMessageEntity basMessageEntity = BeanUtil.copyProperties(updateDTO, BasMessageEntity.class);
        basMessageEntity.setUserId(user.getUserId());
        basMessageEntity.setUsername(user.getUsername());
        boolean save = basMessageService.save(basMessageEntity);
        if (save){
            return R.ok();
        }
        return R.error(messageProperties.getFormSaveError());
    }

    @ApiOperation("留言列表")
    @GetMapping("/MessageList")
    public R MessageList(){
        LambdaQueryWrapper<BasMessageEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BasMessageEntity::getMessageType,1);
        List<BasMessageEntity> pageResult = basMessageService.list(wrapper);
        return R.ok().put("data", pageResult);
    }
}
