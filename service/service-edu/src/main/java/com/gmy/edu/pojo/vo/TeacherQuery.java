package com.gmy.edu.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/1 17:04
 */
@Data
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
public class TeacherQuery {

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private String begin;

    @ApiModelProperty(value = "更新时间")
    private String end;
}
