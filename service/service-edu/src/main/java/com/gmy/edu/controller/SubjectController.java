package com.gmy.edu.controller;


import com.gmy.commonutils.R;
import com.gmy.edu.pojo.subject.OneSubjcet;
import com.gmy.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2021-10-06
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
@Api(tags="课程分类管理")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.importSubjectData(file,subjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    @ApiOperation(value = "课程分类")
    @GetMapping("getAllSubject")
    public R getAllSubject() {

        List<OneSubjcet> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

