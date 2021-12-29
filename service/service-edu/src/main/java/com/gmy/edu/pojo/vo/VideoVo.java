package com.gmy.edu.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {

    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "小节标题")
    private String title;
}
