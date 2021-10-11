package com.gmy.edu.service;

import com.gmy.edu.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.edu.pojo.subject.OneSubjcet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gmy
 * @since 2021-10-06
 */
public interface SubjectService extends IService<Subject> {


    void importSubjectData(MultipartFile file, SubjectService subjectService);

    List<OneSubjcet> getAllOneTwoSubject();
}
