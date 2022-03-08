package com.example.demo.solomon.config;

import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

@Configuration 
@MapperScan(basePackages = "com.example.demo.*.service.dao")
public class SolomonConfig {
	
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;
    @Value("${spring.datasource.url}")
    private String dbJdbcUrl;
    
    @Value("${spring.datasource.hikari.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.hikari.password}")
    private String dbPassword;
	
    @SuppressWarnings("rawtypes")
	@Bean(name = "dataSource")
    public DataSource dataSource() {
    	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    	
    	dataSourceBuilder.driverClassName(dbDriverClassName);
        dataSourceBuilder.url(dbJdbcUrl);
        dataSourceBuilder.username(dbUsername);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

	@Bean 
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception { 
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean(); 
		sqlSessionFactory.setDataSource(datasource); 
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml")); 
		return sqlSessionFactory.getObject(); 
		} 
	@Bean 
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) { 
		return new SqlSessionTemplate(sqlSessionFactory); 
		} 
	}

