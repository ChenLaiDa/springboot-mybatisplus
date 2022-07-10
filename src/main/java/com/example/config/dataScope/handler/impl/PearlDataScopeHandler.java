package com.example.config.dataScope.handler.impl;

import com.example.model.vo.LoginUser;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:23
 */
public class PearlDataScopeHandler extends AbstractDataScopeHandler{
    /**
     * 构建 部门 = xx 表达式
     * @param plainSelect 普通查询对象
     */
    @Override
    public void buildDeptEqualToExpression(PlainSelect plainSelect) {
        LoginUser loginUser = getLoginUser();
        buildEqualToExpression(plainSelect, defaultDeptFiled, new StringValue(loginUser.getOrgCode()));
    }
    /**
     * 构建 创建用户 = xx 表达式
     * @param plainSelect 普通查询对象
     */
    @Override
    public void buildUserEqualToExpression(PlainSelect plainSelect) {
        LoginUser loginUser = getLoginUser();
        buildEqualToExpression(plainSelect, "create_by", new StringValue(loginUser.getUsername()));
    }



    /**
     * 获取 ItemsList，由子类实现
     *
     * @return ItemsList IN 表达式元素集合
     */
    @Override
    ItemsList renderInExpressionList() {
        LoginUser user = getLoginUser();
        List<String> scopeDeptId = user.getScopeDeptId();
        if (CollectionUtils.isNotEmpty(scopeDeptId)){
            List convertList = new ArrayList<>();
            for (String value : scopeDeptId) {
                convertList.add(new StringValue(value));
            }
            return new ExpressionList(convertList);
        }
        return new ExpressionList();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    protected LoginUser getLoginUser(){
//        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        LoginUser loginUser = new LoginUser();
        List<String> scopeDeptIdList = new ArrayList<>();
        scopeDeptIdList.add("A01");
        loginUser.setScopeDeptId(scopeDeptIdList);
        return loginUser;
    }
}
