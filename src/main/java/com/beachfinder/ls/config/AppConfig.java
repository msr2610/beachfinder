package com.beachfinder.ls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.beachfinder.ls.service.impl.InitMethodExampleBean;

@Configuration
@Profile("development")
@ComponentScan //// (basePackageClasses = InitMethodExampleBean.class)
public class AppConfig {
	
	@Bean
	public InitMethodExampleBean initMethodExampleBean () {
		System.out.println("From getInitMethodExampleBean c'tor ");
		return new InitMethodExampleBean();
	} // method getInitMethodExampleBean
	
	@Bean
	public MyBean1 myBean1 () {
		System.out.println("From MyBean class ");
		return new MyBean1();
	} // getMyBean1

} // class AppConfig
