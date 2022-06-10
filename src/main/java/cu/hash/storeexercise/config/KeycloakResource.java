package cu.hash.storeexercise.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Component;

@Component
@Getter
public class KeycloakResource {

    @Value("${keycloak.config.uri}")
    private String uri;

    @Value("${keycloak.config.realms}")
    private String realms;

    @Value("${keycloak.config.protocol}")
    private String protocol;

    @Value("${keycloak.config.client_id}")
    private String clientId;

    @Value("${keycloak.config.grant_type}")
    private String grantType;


    public String getUrlFormatted(){
        return this.uri+"/realms/"+this.getRealms()+"/protocol/"+this.protocol+"/token";
    }
}
