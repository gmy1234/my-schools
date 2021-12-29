package com.gmy.edu.controller;


import com.gmy.commonutils.R;
import com.gmy.edu.pojo.vo.ChapterVo;
import com.gmy.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程章节表：存储课程章节信息
 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping(value = "getChapterVideo/{courseId}",method = RequestMethod.GET)
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }
}

