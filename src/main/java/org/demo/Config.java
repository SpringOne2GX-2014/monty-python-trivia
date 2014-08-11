package org.demo;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Config {

//	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
//    <property name="driverClassName" value="org.hsqldb.jdbcDriver" />
//    <property name="url" value="jdbc:hsqldb:mem:testdb;sql.enforce_strict_size=true;hsqldb.tx=mvcc" />
//    <property name="username" value="sa" />
//    <property name="password" value="" />
//</bean>	

//	public DataSource dataSource() {
//		 EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
//	     .setType(EmbeddedDatabaseType.H2)
//	     .setScriptEncoding("UTF-8")
//	     .ignoreFailedDrops(true)
//	     .addDefaultScripts()
//	     .build();	
//	
//		 return db;
//	}
	
}
