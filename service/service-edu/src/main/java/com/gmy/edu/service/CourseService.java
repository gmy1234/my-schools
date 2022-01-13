package com.gmy.edu.service;

import com.gmy.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.edu.pojo.vo.CourseInfoVo;

/**
 * <p>
 * 课程表：cin存储课程基本信息 服务类
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
public interface CourseService extends IService<Course> {

    String insertCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程ID查课程详情
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseDetail(String courseId);

    /**
     * 修改课程详情
     * @param info
     */
    void updateDetail(CourseInfoVo info);
}
