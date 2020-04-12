package com.iammybest.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by MrDeng on 2017/3/10.
 */

@Configuration
@EnableSwagger2 // 启用 Swagger
public class SwaggerConfig {

    private static String TITLE = "MrDeng Web测试项目 ";
    private static String DESCRIPTION = "采用SpringMVC实现";
    private static String AUTHOR_NAME = "MrDeng";
    private static String AUTHOR_URL = "";
    private static String AUTHOR_EMAIL = "396187665@qq.com";
    private static String VERSION = "1.0";
    @Bean
    public Docket solrApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("认证)")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/auth")// base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iammybest.spring.mvc.controller.user"))
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .apiInfo(SolrApiInfo());
    }


    private ApiInfo SolrApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE+"用户认证")//大标题
                .description(DESCRIPTION)//详细描述
                .version(VERSION)//版本
//                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact(AUTHOR_NAME, AUTHOR_URL, AUTHOR_EMAIL))//作者
//                .license("")
//                .licenseUrl("")
                .build();
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
