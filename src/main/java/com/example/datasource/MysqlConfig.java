package com.example.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @描述: 本地 mysql 配置
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-12-25
 * @创建时间: 09:14
 */
@Configuration
@MapperScan(basePackages = "com.example.dao.mysql", sqlSessionTemplateRef  = "template1")
public class MysqlConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(MysqlConfig.class);


    /**
     * mapper 映射路径
     */
    private final String MAPPER_PATH = "classpath:mapper/mysql/**/*.xml";

    /**
     * 获取全局 mybatis 配置
     */
    @Value("${global.mybatis-config}")
    private String MYBATIS_CONFIG;



    /**
     * 获取全局 model 映射配置
     */
    @Value("${global.alias-model}")
    private String ALIAS_MODEL;


    /**
     *
     * @return
     */
    @Bean(name = "dbProp1")
    @Primary
    @ConfigurationProperties(prefix = "mysql")
    public DataSourceProperties dataSourceProperties(){

        return new DataSourceProperties();
    }


    /**
     * 创建数据源
     * @return
     */
    @Bean(name = "db1")
    @Primary
    @ConfigurationProperties(prefix = "mysql")
    public DataSource dataSource() {

        LOGGER.info("创建 mysql 数据源");
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }



    /**
     * 创建事务管理器
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "manager1")
    public DataSourceTransactionManager transactionManager(@Qualifier("db1") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }



    /**
     * 创建 session 工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "factory1")
    public SqlSessionFactory sessionFactory(@Qualifier("db1") DataSource dataSource)
            throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

            bean.setTypeAliasesPackage(ALIAS_MODEL);
            bean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
            bean.setDataSource(dataSource);

        //分页插件


        return bean.getObject();
    }



    /**
     * 创建 session 模板
     * @param factory
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "template1")
    public SqlSessionTemplate mysqlTemplate(@Qualifier("factory1") SqlSessionFactory factory) {

        return new SqlSessionTemplate(factory);
    }



}
