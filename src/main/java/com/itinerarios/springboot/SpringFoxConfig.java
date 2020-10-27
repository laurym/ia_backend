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
				.description("Esta página lista todos los endpoints que disponibiliza gestión de itinerarios.	"
						+ " \n\n LISTA DE CAMBIOS  "
						+ "  \n\n 13-10-2020 : Agregado de comentarios en swagger."
						+ "  \n\n 13-10-2020 : Cambio de url. Se eliminó el /rest de los endpoints. Tener en cuenta esto al consumir la API."
						+ "  \n\n 22-10-2020 : Contrato de confirmarVuelo. Para verificación. Implementacion no completa."
						+ "  \n\n 23-10-2020 : Contrato de modificaciones / cancelaciones. Para verificación. Implementacion no completa."
						+ "  \n\n 26-10-2020 : Confirmación implementacion completa."
						+ "	").version("1.0-SNAPSHOT").build();
	}

}