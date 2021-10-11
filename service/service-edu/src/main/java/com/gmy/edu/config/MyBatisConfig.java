package com.gmy.edu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/1 15:13
 */

@Configuration
@MapperScan("com.gmy.edu.mapper")
public class MyBatisConfig  {

    // 逻辑删除的插件：
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    // 分页功能
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
