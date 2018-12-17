package com.example.multidatabase.multidatasource.interceptor;

import com.example.multidatabase.multidatasource.datasource.DynamicDataSourceGlobal;
import com.example.multidatabase.multidatasource.datasource.DynamicDataSourceHolder;
import com.example.multidatabase.multidatasource.util.ReflectionUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangkun
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DynamicPlugin implements Interceptor {

    protected static final Logger logger = LoggerFactory.getLogger(DynamicPlugin.class);
    //private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";


    private static final Map<String, DynamicDataSourceGlobal> cacheMap = new ConcurrentHashMap<>();


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();
        StatementHandler delegate = (StatementHandler) ReflectionUtils
                .getFieldValue(statementHandler, "delegate");
        MappedStatement ms = (MappedStatement) ReflectionUtils.getFieldValue(
                delegate, "mappedStatement");
        DynamicDataSourceGlobal dynamicDataSourceGlobal = null;
        if ((dynamicDataSourceGlobal = cacheMap.get(ms.getId())) == null) {
            //读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
            } else {
                dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
            }
            cacheMap.put(ms.getId(), dynamicDataSourceGlobal);
        }
        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
