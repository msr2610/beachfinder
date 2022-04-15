package com.beachfinder.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class LsApp {
	
	 @Autowired
	    private static ApplicationContext appContext;

    public static void main(final String... args) {

    	appContext = SpringApplication.run(LsApp.class, args);
        
        displayAllBeans();
        
    } // main
    
    // Method to display all bean names
    public static void displayAllBeans() {

        String[] beans = appContext.getBeanDefinitionNames();
        int i = 0;
        for (String beanname : beans) {
        	i++;
            System.out.println("*********++ From within LsApp class ~~ - " + i + " " + beanname);
        } // for

        
                
    } // run

}
