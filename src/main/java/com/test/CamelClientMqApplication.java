package com.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.osgi.io.OsgiBundleResourcePatternResolver;

@SpringBootApplication
@ImportResource("classpath:camel-context.xml")
public class CamelClientMqApplication implements BundleActivator {

	ConfigurableApplicationContext appContext;
	
	public static void main(String[] args) {
		SpringApplication.run(CamelClientMqApplication.class, args);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		
		 Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
	        // trick to enable scan: get osgi resource pattern resolver
	        OsgiBundleResourcePatternResolver resourceResolver = new OsgiBundleResourcePatternResolver(context.getBundle());
	        // and provide it to spring application
	        appContext = new SpringApplication(resourceResolver, CamelClientMqApplication.class).run();
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		SpringApplication.exit(appContext, () -> 0);
		
	}

}
