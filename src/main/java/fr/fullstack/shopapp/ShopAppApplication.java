package fr.fullstack.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ShopAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAppApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("fr.fullstack.shopapp.controller"))
			.paths(PathSelectors.any())
			.build();
	}

}

//https://www.baeldung.com/spring-boot-start
//https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3
//https://medium.com/nerd-for-tech/building-a-simple-restful-api-with-spring-boot-2351687ecab0
//https://www.google.com/search?q=spring+boot+tree+structure+rest&client=firefox-b-d&sxsrf=ALiCzsbIkmTEoMy_ZPj-WUwh1Asp8UeyOg:1667032755918&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi69Pr5hIX7AhXFwoUKHchjBYoQ_AUoAXoECAEQAw&biw=1920&bih=955&dpr=1#imgrc=2F9T08zHzrGI-M