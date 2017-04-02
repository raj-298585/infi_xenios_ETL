package com.infi.xenios.batch.configure;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AppConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public LoadIPJobConfig loadIPJobConfig;

	@Bean
	public Job stgLoadIpJob() {
		return jobBuilderFactory.get("loadIp").start(loadIPJobConfig.loadIPInfoStep()).build();
	}

}
