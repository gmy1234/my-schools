package com.gmy.edu.service.impl;

import com.gmy.edu.pojo.Chapter;
import com.gmy.edu.mapper.ChapterMapper;
import com.gmy.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程章节表：存储课程章节信息
 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2021-10-10
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

}
