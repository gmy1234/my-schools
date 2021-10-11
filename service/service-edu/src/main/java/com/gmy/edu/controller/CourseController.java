package com.gmy.edu.controller;


import com.gmy.commonutils.R;
import com.gmy.edu.pojo.vo.CourseInfoVo;
import com.gmy.edu.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程表：cin存储课程基本信息 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 添加课程基本信息的方法
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.insertCourseInfo(courseInfoVo);

        return R.ok();
    }

}

