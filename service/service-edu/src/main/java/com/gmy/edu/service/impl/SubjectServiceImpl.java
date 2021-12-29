package com.gmy.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.gmy.edu.listener.SubjectExcelListener;
import com.gmy.edu.pojo.Subject;
import com.gmy.edu.mapper.SubjectMapper;
import com.gmy.edu.pojo.excel.SubjectData;
import com.gmy.edu.pojo.subject.OneSubjcet;
import com.gmy.edu.pojo.subject.TwoSubjcet;
import com.gmy.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.pojo.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2021-10-06
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public void importSubjectData(MultipartFile file, SubjectService subjectService) {

        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20002, "添加课程分类失败");
        }

    }

    /**
     * 课程分类 列表（ 树形 ）
     * 查询
     *
     * @return
     */
    @Override
    public List<OneSubjcet> getAllOneTwoSubject() {
//        1。查询出一级分类的
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<Subject> subjectOneList = baseMapper.selectList(wrapperOne);
//        2.查询出二级分类的
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<Subject> subjectTwoList = baseMapper.selectList(wrapperTwo);

        List<OneSubjcet> finalSubjectList = new ArrayList<>();
//        3.封装一级分类
        // subjectOneList  =>>>>>  finalSubjectList

        for (int i = 0; i < subjectOneList.size(); i++) {
            Subject subject = subjectOneList.get(i);

            // 把subject 遍历出来 ，放到 finalSubjectList 中
            OneSubjcet oneSubjcet = new OneSubjcet();
            //oneSubjcet.setId(subject.getId());
            //oneSubjcet.setTitle(subject.getTitle());
            // 属性拷贝，=上边两行代码
            BeanUtils.copyProperties(subject, oneSubjcet);
            // 把 onesubjcet 放到 finalSubjectList 里边
            finalSubjectList.add(oneSubjcet);

            // 4.封装二级分类
            List<TwoSubjcet> finalTwoSubjectList = new ArrayList<>();
            for (int j = 0; j < subjectTwoList.size(); j++) {
                Subject tSubject = subjectTwoList.get(j);

                // 判断 二级分类的pid 和一分类的id是否==
                if (tSubject.getParentId().equals(subject.getId())) {
                    TwoSubjcet twoSubjcet = new TwoSubjcet();

                    BeanUtils.copyProperties(tSubject, twoSubjcet);
                    finalTwoSubjectList.add(twoSubjcet);
                }

            }
            // 把一级分类下的二级分类 都添加到 一级分类里边
            oneSubjcet.setChildren(finalTwoSubjectList);
        }
        return finalSubjectList;
    }
}
