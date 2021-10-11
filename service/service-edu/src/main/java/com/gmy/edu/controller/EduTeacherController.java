package com.gmy.edu.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gmy.commonutils.R;
import com.gmy.edu.pojo.EduTeacher;
import com.gmy.edu.pojo.vo.TeacherQuery;
import com.gmy.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/1 11:41
 */

@CrossOrigin
@RestController
@RequestMapping("/eduService/teacher")
@Api(tags="讲师管理")
public class EduTeacherController {

    @Autowired
    public EduTeacherService eduTeacherService;

    @GetMapping("/getAll")
    @ApiOperation(value = "所有讲师列表")
    public R getALlTeacher(){

        final List<EduTeacher> list = eduTeacherService.list(null);


        return R.ok().data("teachers",list);
    }

    @DeleteMapping ("/deleted/{id}")
    @ApiOperation(value = "通过id 进行删除")
    public R deleteById(@PathVariable("id" ) String id){

        // 先判断id 在不在：
        final EduTeacher byId = eduTeacherService.getById(id);
        if( byId ==null){
            return R.error();
        }
        final boolean b = eduTeacherService.removeById(id);
        return R.ok();
    }


    // 3.讲师分页功能：
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/pageTeacher/{stratIndex}/{pageSize}")
    public R QueryTeacherPage(@PathVariable("stratIndex") int stratIndex,
                              @PathVariable("pageSize") int pageSize){

        // 先创建配置对象
        final Page<EduTeacher> page = new Page<>(stratIndex,pageSize);
        // 调用service中的 分页方法
        // 分页的数据，已经封装给了page对象
        eduTeacherService.page(page,null);
//        然后进得到据的总量
        final long total = page.getTotal();
        // 记录数据
        final List<EduTeacher> records = page.getRecords();

        return  R.ok().data("total",total).data("records",records);
    }

    // 4. 条件查询
    @ApiOperation(value = "条件查询")
    @GetMapping("/conditionQuery")
    public R conditionQuery(TeacherQuery teacherQuery){

        //1.构造条件：
        final QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();


        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.lt("gmt_modified",end);
        }

        // 2. 查询
        final List<EduTeacher> list = eduTeacherService.list(wrapper);


        return  R.ok().data("teachers",list);
    }


    @GetMapping("/conditionPage/{stratIndex}/{pageSize}")
    public R queryConditionPage(@PathVariable("stratIndex") int stratIndex,
                                @PathVariable("pageSize") int pageSize,
                                @RequestBody(required = false) TeacherQuery teacherQuery){

//        1.创建分页
        final Page<EduTeacher> page = new Page<>(stratIndex,pageSize);
        // 创建条件
        final QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        if(teacherQuery == null){
            // 调用service中的 分页方法
            // 分页的数据，已经封装给了page对象
            eduTeacherService.page(page,null);
//        然后进得到据的总量
            final long total = page.getTotal();
            // 记录数据
            final List<EduTeacher> records = page.getRecords();

            return  R.ok().data("total",total).data("records",records);
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();


        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.lt("gmt_modified",end);
        }

        wrapper.orderByDesc("gmt_modified");
        eduTeacherService.page(page,wrapper);

         long total = page.getTotal();
         List<EduTeacher> records = page.getRecords();

        return  R.ok().data("total",total).data("records",records);
    }


    // 6.添加
    @PostMapping("/addTeacher")
    public R addTeacher( @RequestBody EduTeacher eduTeacher){

        final boolean save = eduTeacherService.save(eduTeacher);

        return R.ok();


    }


    // 根据 id 查询
    @GetMapping("/{id}")
    public R queryById( @PathVariable("id") String id){
        final EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);

    }


    // 根据id修改1
    @PutMapping("/updateTeacher/{id}")
    public R update(@PathVariable ("id") String id,
                    @RequestBody  EduTeacher eduTeacher){

        eduTeacher.setId(id);
        final boolean b = eduTeacherService.updateById(eduTeacher);
        if( b ){

        }

        return R.ok();
    }

    // 根据id修改2
    @PostMapping("/updateTeacher")
    public R updateTeacher( @RequestBody  EduTeacher eduTeacher){

        final boolean b = eduTeacherService.updateById(eduTeacher);

        if( b ){
            return R.ok();
        }else {
            return R.error();
        }


    }

}
