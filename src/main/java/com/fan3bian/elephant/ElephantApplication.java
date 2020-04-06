package com.fan3bian.elephant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {"com.jd.fpl.cache.client", "com.fan3bian.elephant"})
@ImportResource(locations = {"classpath:/spring-jimdb.xml"})
public class ElephantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElephantApplication.class, args);
    }

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		// 目的是
//		return args -> {
//			System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			Arrays.stream(beanNames).forEach(System.out::println);
//		};
//	}

}

