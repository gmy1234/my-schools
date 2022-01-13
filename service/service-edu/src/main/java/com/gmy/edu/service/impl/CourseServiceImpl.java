package com.gmy.edu.service.impl;

import com.gmy.edu.pojo.Chapter;
import com.gmy.edu.pojo.Course;
import com.gmy.edu.mapper.CourseMapper;
import com.gmy.edu.pojo.CourseDescription;
import com.gmy.edu.pojo.vo.CourseInfoVo;
import com.gmy.edu.service.CourseDescriptionService;
import com.gmy.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.pojo.GuliException;
import org.apache.commons.lang3.StringUtils;
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
    public String insertCourseInfo(CourseInfoVo courseInfoVo) {
        // 向 课程表中 添加数据：
        Course course = new Course();
        // 把courseInfoVo 对象 转换成 course
        BeanUtils.copyProperties(courseInfoVo,course);
        course.setIsDeleted(0);
        int count = this.baseMapper.insert(course);
        if (count == 0){
            throw new GuliException(20001, "添加课程信息失败");
        }

        // 2. 向课程简介中添加描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //添加的是course ，需要course 的id；
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }

    /**
     * 根据课程ID查课程详情
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseDetail(String courseId) {
        CourseInfoVo response = new CourseInfoVo();
        // 查询课程表
        Course courseInfo = this.baseMapper.selectById(courseId);
        // 课程描述表
        CourseDescription description = courseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(courseInfo, response);
        response.setDescription(description.getDescription());
        return response;
    }

    @Override
    public void updateDetail(CourseInfoVo info) {
        Course course = new Course();
        BeanUtils.copyProperties(info, course);
        // 修改课程表
        int x = this.baseMapper.updateById(course);
        if (x == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }
        CourseDescription description = new CourseDescription();
        description.setId(info.getId());
        description.setDescription(info.getDescription());
        // 修改描述
        courseDescriptionService.updateById(description);
    }
}
