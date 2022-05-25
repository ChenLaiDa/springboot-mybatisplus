package com.example.utils;


import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

public class Prasing_Test {

    public static void main(String[] args) {
        String sql = "select * from sys_user order by create_time desc";
        try {
            Select parse = (Select)CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }


}
