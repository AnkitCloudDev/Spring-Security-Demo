package com.luv2code.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// define a bean for ViewResolver
	@Autowired
private Environment environment; // Hold data from properties

	private Logger logger=Logger.getLogger(getClass().getName());

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

//	Define a bean for our security Data Source
@Bean
	public DataSource securityDataSource()  {
//	Create a Connection Pool
	ComboPooledDataSource comboPooledDataSourc=new ComboPooledDataSource();

//	Set the JDBC Driver Class
try {
	comboPooledDataSourc.setDriverClass(environment.getProperty("jdbc.driver"));

}catch (PropertyVetoException e)
{
	throw new RuntimeException(e);
}
//	Logg the connection Properties
	logger.info("***JDBC URL: "+ environment.getProperty("jdbc.url"));
	logger.info("***JDBC User: "+ environment.getProperty("jdbc.user"));
//	Set DB conneciton props
comboPooledDataSourc.setJdbcUrl(environment.getProperty("jdbc.url"));
	comboPooledDataSourc.setUser(environment.getProperty("jdbc.user"));
	comboPooledDataSourc.setPassword(environment.getProperty("jdbc.password"));
//	Set Connection Pool props
	comboPooledDataSourc.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

	comboPooledDataSourc.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

	comboPooledDataSourc.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

	comboPooledDataSourc.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

	return comboPooledDataSourc;
}
private int getIntProperty(String propertyName)
{
	String propval=environment.getProperty(propertyName);
	int intval=Integer.parseInt(propval);
	return intval;
}
	
}









