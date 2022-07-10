package com.example.config.dataScope.interceptor;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.example.config.dataScope.DataScope;
import com.example.config.dataScope.DataScopeContext;
import com.example.config.dataScope.handler.IDataScopeHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:42
 */
@Slf4j
public class DataScopeInterceptor extends JsqlParserSupport implements InnerInterceptor {
    /**
     * 数据权限处理器
     */
    private IDataScopeHandler dataScopeHandler;

    public DataScopeInterceptor(IDataScopeHandler dataScopeHandler) {
        if (null != dataScopeHandler) {
            this.dataScopeHandler = dataScopeHandler;
        }
    }

    /**
     * 查询之前
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        // 1. 查询是否配置了忽略@InterceptorIgnore
        if (!InterceptorIgnoreHelper.willIgnoreDataPermission(ms.getId())) {
            // 2. 获取BoundSql
            PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
            // 3. 获取新的SQL 并设置到BoundSql对象中
            mpBs.sql(this.parserSingle(mpBs.sql(), ms.getId()));
        }
    }

    /**
     * 处理查询
     *
     * @param select 查询对象Select
     * @param index  序列
     * @param sql    语句：
     * @param obj    查询Mapper方法全路径
     */
    @SneakyThrows
    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        this.processSelectBody(select.getSelectBody(), obj);
        List<WithItem> withItemsList = select.getWithItemsList();
        if (!CollectionUtils.isEmpty(withItemsList)) {
            withItemsList.forEach(e -> {
                try {
                    processSelectBody(e, obj);
                } catch (JSQLParserException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    /**
     * 处理  selectBody
     *
     * @param selectBody
     */
    void processSelectBody(SelectBody selectBody, Object obj) throws JSQLParserException {
        if (selectBody != null) {
            if (selectBody instanceof PlainSelect) {
                this.processPlainSelect((PlainSelect) selectBody, obj);
            } else if (selectBody instanceof WithItem) {
                WithItem withItem = (WithItem) selectBody;
                // 处理每个With 语句的查询
                this.processSelectBody(withItem.getSelectBody(), obj);
            } else {
                SetOperationList operationList = (SetOperationList) selectBody;
                if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                    operationList.getSelects().forEach(e -> {
                        try {
                            processSelectBody(e, obj);
                        } catch (JSQLParserException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }
        }
    }

    /**
     * 处理查询 添加 WHERE 条件
     *
     * @param plainSelect
     * @param obj
     * @throws JSQLParserException
     */
    protected void processPlainSelect(PlainSelect plainSelect, Object obj) throws JSQLParserException {
        String methodName = obj.toString();

        // 如果不存在注解， 不处理
        DataScope dataScope = DataScopeContext.getDataScopeContext();
        if (dataScope == null){
            return;
        }

        if (dataScope == DataScope.SUB_ALL_DEPT){  // 本部门及下级部门
            dataScopeHandler.buildInExpression(plainSelect);
        }else if(dataScope == DataScope.SERF_DEPT){  // 本部门
            dataScopeHandler.buildDeptEqualToExpression(plainSelect);
        }else if(dataScope == DataScope.SERF){  // 本人
            dataScopeHandler.buildUserEqualToExpression(plainSelect);
        }

        DataScopeContext.clear();
    }





}
