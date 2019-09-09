package com.david.davidappbatch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;

//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//@ConditionalOnProperty(name = "job.name", havingValue = JOB_NAME)
public class PayPagingFailJobConfiguration {

//    public static final String JOB_NAME = "payPagingFailJob";
//
//    //private final EntityManagerFactory entityManagerFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final int chunkSize = 10;
//
//    @Bean
//    public Job payPagingJob() {
//        return jobBuilderFactory.get(JOB_NAME)
//                .start(payPagingStep())
//                .build();
//    }

//    @Bean
//    @JobScope
//    public Step payPagingStep() {
//        return stepBuilderFactory.get("payPagingStep")
//                .<Pay, Pay>chunk(chunkSize)
//                .reader(payPagingReader())
//                .processor(payPagingProcessor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Pay> payPagingReader() {
//        return new JpaPagingItemReaderBuilder<Pay>()
//                .queryString("SELECT p FROM Pay p WHERE p.successStatus = false")
//                .pageSize(chunkSize)
//                .entityManagerFactory(entityManagerFactory)
//                .name("payPagingReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Pay, Pay> payPagingProcessor() {
//        return item -> {
//            item.success();
//            return item;
//        };
//    }
//
//    @Bean
//    @StepScope
//    public JpaItemWriter<Pay> writer() {
//        JpaItemWriter<Pay> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManagerFactory);
//        return writer;
//    }
}