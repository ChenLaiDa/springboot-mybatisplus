package com.example.config.dataScope;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:15
 */
public class DataScopeContext {

        private static ThreadLocal<DataScope> currentTenant = new ThreadLocal<>();

        public static void setDataScopeContext(DataScope dataScope){
            currentTenant.set(dataScope);
        }

        public static DataScope getDataScopeContext(){
            return currentTenant.get();
        }

        public static void clear(){
            currentTenant.remove();
        }
}
