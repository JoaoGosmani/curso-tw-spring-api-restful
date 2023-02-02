package br.com.joaogosmani.jgprojetos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    private static final String CONTATO_EMAIL = "joaogosmani@gmail.com";
    private static final String CONTATO_URL = "joaogosmani@gmail.com";
    private static final String CONTATO_NOME = "JoaoGosmani";
    private static final String API_VERSAO = "1.0.0";
    private static final String API_DESCRICAO = "API de Gerenciamento de Projetos";
    private static final String API_TITULO = "JG Projetos";
    private static final String PACOTE_BASE = "br.com.joaogosmani.jgprojetos.api";

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(PACOTE_BASE))
            .paths(PathSelectors.any())
            .build().apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title(API_TITULO)
            .description(API_DESCRICAO)
            .version(API_VERSAO)
            .contact(new Contact(CONTATO_NOME, CONTATO_URL, CONTATO_EMAIL))
            .build();
    }

}
