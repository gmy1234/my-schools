package com.gmy.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gmy.edu.pojo.Chapter;
import com.gmy.edu.mapper.ChapterMapper;
import com.gmy.edu.pojo.CourseDescription;
import com.gmy.edu.pojo.Video;
import com.gmy.edu.pojo.vo.ChapterVo;
import com.gmy.edu.pojo.vo.CourseInfoVo;
import com.gmy.edu.pojo.vo.VideoVo;
import com.gmy.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.edu.service.CourseDescriptionService;
import com.gmy.edu.service.VideoService;
import com.gmy.pojo.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        // 最终数据
        List<ChapterVo> response = new ArrayList<>();

        // 根据课程Id 获取课程里所有的章节
        LambdaQueryWrapper<Chapter> queryWrapperChapter = new LambdaQueryWrapper<>();
        queryWrapperChapter.eq(Chapter::getCourseId, courseId);
        List<Chapter> chapters = this.baseMapper.selectList(queryWrapperChapter);

        // 根据课程ID，查出所有的小节
        LambdaQueryWrapper<Video> queryWrapperVideo = new LambdaQueryWrapper<>();
        queryWrapperVideo.eq(Video::getCourseId, courseId);
        List<Video> viedos = videoService.list(queryWrapperVideo);

        // 遍历章节的数据 封装
        for (Chapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            // 拷贝
            BeanUtils.copyProperties(chapter, chapterVo);
            // 数据组装
            response.add(chapterVo);

            //填充课时vo数据
            List<VideoVo> data = new ArrayList<>();
            // 遍历小节的数据 封装
            for (Video viedo : viedos) {
                // 章节中的Id == 小节中的ID
                if (chapter.getId().equals(viedo.getChapterId())) {
                    VideoVo vo = new VideoVo();
                    BeanUtils.copyProperties(viedo, vo);
                    data.add(vo);
                }
            }

            // 一个章节下的所有小节
//            List<Video> allViedosFromChapter = viedos.stream().filter(
//                    video -> Objects.equals(video.getCourseId(), chapter.getId())).collect(Collectors.toList());
//            for (Video video : allViedosFromChapter) {
//                VideoVo vo = new VideoVo();
//                BeanUtils.copyProperties(video, vo);
//                data.add(vo);
//            }
//            chapterVo.setSon(data);
            // 组装数据
            chapterVo.setSon(data);
        }

        return response;
    }

}
