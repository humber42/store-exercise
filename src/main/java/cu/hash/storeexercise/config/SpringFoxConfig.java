package cu.hash.storeexercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    private final SwaggerConfigs configs;


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cu.hash.storeexercise.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(configs.getTitle(),
                configs.getDescription(),
                configs.getVersion(),
                configs.getTermsOfService(),
                new Contact(configs.getContactName(), "",""),
                configs.getLicenseUrl(),
                configs.getLicenseUrl(),
                Collections.emptyList());
    }

    @Autowired
    public SpringFoxConfig(SwaggerConfigs configs){
        this.configs=configs;
    }







}
