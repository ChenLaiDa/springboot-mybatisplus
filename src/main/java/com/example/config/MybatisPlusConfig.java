package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.example.config.dataScope.handler.impl.PearlDataScopeHandler;
import com.example.config.dataScope.interceptor.DataScopeInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/1/28 14:37
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * mybatis-plus 拦截器
     *文档：http://mp.baomidou.com
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //自定义数据权限
        PearlDataScopeHandler dataScopeHandler = new PearlDataScopeHandler();
        interceptor.addInnerInterceptor(new DataScopeInterceptor(dataScopeHandler));

        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }




}
