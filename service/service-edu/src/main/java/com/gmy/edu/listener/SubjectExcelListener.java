package com.gmy.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gmy.edu.pojo.Subject;
import com.gmy.edu.pojo.excel.SubjectData;
import com.gmy.edu.service.SubjectService;
import com.gmy.pojo.GuliException;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/10/6 11:04
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // SubjectExcelListener 不能交给spring 管理 , 自己 new
    // 所以增加构造器。

    public SubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new GuliException(20001,"添加失败,文件数据为空");
        }

        //判断一级分类不能重复添加
        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {  // 一级分类不存在，进行添加。
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //添加二级分类
        String pid = existOneSubject.getId();
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {  // 二级分类不存在，进行添加。
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existTwoSubject);
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

    /**
     * 判断一级分类是否重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existOneSubject(SubjectService subjectService, String name){
        final QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");

        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    /**
     * 判断二级分类 是否 重复
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existTwoSubject(SubjectService subjectService, String name, String pid){
        final QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);

        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
}
