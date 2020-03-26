package com.abc.swagger2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abc"))
                .paths(PathSelectors.any())
                .build();
    }

    /******
     * Api 页面的基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("集成Swagger2构建RestApi")
                .description("这里应该是详细的说明信心，但是不想写了")
                .termsOfServiceUrl("https://www.baidu.com")
                .contact("Mads ApiInfo")
                .version("0.0.0.1")
                .build();
    }
}
