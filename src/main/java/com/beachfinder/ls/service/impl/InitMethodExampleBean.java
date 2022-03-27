package com.beachfinder.ls.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;


public class InitMethodExampleBean {

	private static final Logger LOG = LoggerFactory.getLogger(InitMethodExampleBean.class);

	@Bean(initMethod="init")
	public InitMethodExampleBean getInitMethodExampleBean() {
		System.out.println ("From InitMethodExampleBean - C'tor - Debug println");
	    return new InitMethodExampleBean();
	}

    public void init() {
    	
    	System.out.println ("From InitMethodExampleBean - Debug println");
        LOG.debug("From InitMethodExampleBean - Debug");
        
        LOG.info("From InitMethodExampleBean - Info");
    }
}
