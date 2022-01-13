package com.gmy.edu.service;

import com.gmy.edu.pojo.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.edu.pojo.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节表：存储课程章节信息
 服务类
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
