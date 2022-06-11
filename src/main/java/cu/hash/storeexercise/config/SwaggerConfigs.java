package cu.hash.storeexercise.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SwaggerConfigs {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.term-of-service}")
    private String termsOfService;

    @Value("${swagger.contact-name}")
    private String contactName;

    @Value("${swagger.license-url}")
    private String licenseUrl;
}
