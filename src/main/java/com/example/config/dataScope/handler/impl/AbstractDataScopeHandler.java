package com.example.config.dataScope.handler.impl;

import com.example.config.dataScope.handler.IDataScopeHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:18
 */
public abstract class AbstractDataScopeHandler implements IDataScopeHandler {
    /**
     * 机构编号
     */
    public String defaultDeptFiled = "sys_org_code";

    /**
     * 获取 ItemsList，由子类实现
     *
     * @return ItemsList IN 表达式元素集合
     */
    abstract ItemsList renderInExpressionList();

    /**
     * 构建IN (xxx,xxx) 表达式
     *
     * @param plainSelect 普通查询对象
     */
    @Override
    public void buildInExpression(PlainSelect plainSelect) {
        // 1. 构建In 表达式
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Column aliasColumn = getAliasColumn((Table) fromItem, defaultDeptFiled);
            InExpression inExpression = new InExpression(aliasColumn, renderInExpressionList());

            // 2. 添加新的Where语句
            if (null == plainSelect.getWhere()) {
                plainSelect.setWhere(new Parenthesis(inExpression));
            } else {
                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), inExpression));
            }
        }

    }

    /**
     * 构建 = 表达式
     *
     * @param plainSelect 普通查询对象
     * @param column      列名
     * @param value       值
     */
    @Override
    public void buildEqualToExpression(PlainSelect plainSelect, String column, Expression value) {
        // 1. 构建等于表达式
        FromItem fromItem = plainSelect.getFromItem();
        Column aliasColumn = getAliasColumn((Table) fromItem, column);
        Expression where = plainSelect.getWhere();
        EqualsTo equalsToExpression = new EqualsTo();
        equalsToExpression.setLeftExpression(aliasColumn);
        equalsToExpression.setRightExpression(value);
        // 2. 添加表达式
        if (where == null) {
            plainSelect.setWhere(equalsToExpression);
        } else {
            plainSelect.setWhere(where instanceof OrExpression ? new AndExpression(new Parenthesis(where), equalsToExpression) : new AndExpression(where, equalsToExpression));
        }
    }

    /**
     * 获取带别名的列
     *
     * @param table      表
     * @param columnName 列名
     * @return 新的列名
     */
    public Column getAliasColumn(Table table, String columnName) {
        StringBuilder column = new StringBuilder();
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName()).append(".");
        }
        column.append(columnName);
        return new Column(column.toString());
    }


}
