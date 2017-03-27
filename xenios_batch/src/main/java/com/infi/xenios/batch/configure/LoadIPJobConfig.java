package com.infi.xenios.batch.configure;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.infi.xenios.batch.item.entity.HttpRequestEntity;
import com.infi.xenios.batch.util.ResourceUtil;

@Configuration
public class LoadIPJobConfig {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	public MongoTemplate mongoTemplate;

	@Bean
	public FlatFileItemReader<HttpRequestEntity> itemReader() {
		FlatFileItemReader<HttpRequestEntity> reader = new FlatFileItemReader<>();
		// reader.setResource(new FileSystemResource("student-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<HttpRequestEntity>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "asNumber", "ipCidr", "networkName", "ipVersion", "ipUsageType" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<HttpRequestEntity>() {
					{
						setTargetType(HttpRequestEntity.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	@StepScope
	public MultiResourceItemReader<HttpRequestEntity> loadIpReader() {
		MultiResourceItemReader<HttpRequestEntity> reader = new MultiResourceItemReader<HttpRequestEntity>();
		Resource[] resources = ResourceUtil.readResources("/home/rajendra/RP", "Sheet1");
		reader.setResources(resources);
		reader.setDelegate(itemReader());
		return reader;
	}

	@Bean
	@StepScope
	public MongoItemWriter<HttpRequestEntity> itemWriter() {
		MongoItemWriter<HttpRequestEntity> writer = new MongoItemWriter<HttpRequestEntity>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("report"); // collection (table) name goes here.
		return writer;
	}

	@Bean
	public Step loadIPInfoStep() {
		return stepBuilderFactory.get("loadIPInfo").<HttpRequestEntity, HttpRequestEntity>chunk(10)
				.reader(loadIpReader()).writer(itemWriter()).build();
	}
}
