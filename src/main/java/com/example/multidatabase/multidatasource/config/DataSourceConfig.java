package com.example.multidatabase.multidatasource.config;


import com.example.multidatabase.multidatasource.datasource.DynamicDataSource;
import com.example.multidatabase.multidatasource.datasource.DynamicDataSourceGlobal;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.example.multidatabase.multidatasource.mapper")
public class DataSourceConfig {
    @Bean(name = "dataSourceWrite1")
    @ConfigurationProperties(prefix = "spring.datasource.write1")
    @Primary
    public DataSource dataSourceWrite1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dataSourceRead1")
    @ConfigurationProperties(prefix = "spring.datasource.read1")
    public DataSource dataSourceRead1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dataSource")
    public DynamicDataSource getDynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DynamicDataSourceGlobal.READ.name(), dataSourceRead1());
        dataSourceMap.put(DynamicDataSourceGlobal.WRITE.name(), dataSourceWrite1());
        //传入数据源map，AbstractRoutingDataSource将以key来分配数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }


}

