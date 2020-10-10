package com.itinerarios.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
//          .apis(RequestHandlerSelectors.any())   
          .apis( RequestHandlerSelectors.basePackage( "com.itinerarios.springboot" ) )
          .paths(PathSelectors.any())                          
          .build().tags(new Tag("baseAPI", "API que expone los métodos para los diferentes tenants"), new Tag("vuelosAPI", "API que expone los métodos para la operatoria de vuelos"), new Tag("usuariosAPI", "API que expone los métodos para el manejo de los usuarios"))
          .apiInfo(this.apiInfo());                                           
    }

	// 	Describe your apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Gestión de itinerarios API")
				.description("Esta página lista todos los endpoints que disponibiliza gestión de itinerarios.").version("1.0-SNAPSHOT")
				.build();
	}

}