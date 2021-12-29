package com.gmy.edu.pojo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**一级分类
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/10/6 15:41
 */
@Data
public class OneSubjcet {

    private String id;

    private String title;

    //一个一级分类，有多个二级分类    一对多
    private List<TwoSubjcet> children = new ArrayList<>();
}
