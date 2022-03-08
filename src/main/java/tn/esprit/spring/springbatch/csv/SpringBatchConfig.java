package tn.esprit.spring.springbatch.csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.csv;


@Configuration
@EnableBatchProcessing
@Component
public class SpringBatchConfig {
	@Bean
	    public Job job(JobBuilderFactory jobBuilderFactory,
	                   StepBuilderFactory stepBuilderFactory,
	                   ItemReader<csv> itemReader,
	                   ItemProcessor<csv, csv> itemProcessor,
	                   ItemWriter<csv> itemWriter
	    ) {

	        Step step = stepBuilderFactory.get("ETL-file-load")
	                .<csv, csv>chunk(100)
	                .reader(itemReader)
	                .processor(itemProcessor)
	                .writer(itemWriter)
	                .build();


	        return jobBuilderFactory.get("ETL-Load")
	                .incrementer(new RunIdIncrementer())
	                .start(step)
	                .build();
	    }
	 @Bean
	    public FlatFileItemReader<csv> itemReader() {
	    	
	        FlatFileItemReader<csv> flatFileItemReader = new FlatFileItemReader<>();
	    	flatFileItemReader.setResource(new ClassPathResource("csv.csv"));
	        flatFileItemReader.setName("CSV-Reader");
	        flatFileItemReader.setLinesToSkip(1);
	        flatFileItemReader.setLineMapper(lineMapper());
	        return flatFileItemReader;
	    }
	    
	    @Bean
	    public LineMapper<csv> lineMapper() {

	        DefaultLineMapper<csv> defaultLineMapper = new DefaultLineMapper<>();
	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

	        lineTokenizer.setDelimiter(",");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames("name","email","number");

	        BeanWrapperFieldSetMapper<csv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(csv.class);

	        defaultLineMapper.setLineTokenizer(lineTokenizer);
	        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

	        return defaultLineMapper;
	    }
}
