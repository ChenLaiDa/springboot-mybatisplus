package com.example.config.dataScope.handler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:18
 */
public interface IDataScopeHandler {
    /**
     * 构建IN (xxx,xxx) 表达式
     *
     * @param plainSelect       普通查询对象
     */
    void buildInExpression(PlainSelect plainSelect);


    /**
     * 构建 = 表达式
     *
     * @param plainSelect 普通查询对象
     * @param column      列名
     * @param value       值
     */
    void buildEqualToExpression(PlainSelect plainSelect, String column, Expression value);

    /**
     * 构建 部门 = xx 表达式
     *
     * @param plainSelect       普通查询对象
     */
    void buildDeptEqualToExpression(PlainSelect plainSelect);

    /**
     * 构建 创建用户 = xx 表达式
     *
     * @param plainSelect 普通查询对象
     */
    void buildUserEqualToExpression(PlainSelect plainSelect);
}
