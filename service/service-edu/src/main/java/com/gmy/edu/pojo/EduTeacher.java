package com.gmy.edu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/1 11:41
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value="EduTeacher对象", description="讲师")
public class EduTeacher {

    @ApiModelProperty("讲师id")
    // id 自增
    @TableId(value = "id",type = IdType.ID_WORKER_STR )
    private String id;

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    // 自动添加
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
