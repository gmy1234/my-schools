package com.gmy.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/14 13:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
