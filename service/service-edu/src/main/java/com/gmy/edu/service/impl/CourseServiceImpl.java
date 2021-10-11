package com.gmy.edu.service.impl;

import com.gmy.edu.pojo.Course;
import com.gmy.edu.mapper.CourseMapper;
import com.gmy.edu.pojo.CourseDescription;
import com.gmy.edu.pojo.vo.CourseInfoVo;
import com.gmy.edu.service.CourseDescriptionService;
import com.gmy.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.pojo.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表：cin存储课程基本信息 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseDescriptionService courseDescriptionService;

    @Override
    public void insertCourseInfo(CourseInfoVo courseInfoVo) {

        // 向 课程中 添加方法：
        Course course = new Course();
        // 把courseInfoVo 对象 转换成 course

        BeanUtils.copyProperties(courseInfoVo,course);
        int count = baseMapper.insert(course);

        if (count == 0){
            throw new GuliException(20001, "添加课程信息失败");
        }

        // 2. 向课程简介中 添加  描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //添加的是course ，需要course 的id；
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);


    }
}
