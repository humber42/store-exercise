package cu.hash.storeexercise.controllers.login;

import cu.hash.storeexercise.config.KeycloakResource;
import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.exceptions.UserAndPasswordWrongException;
import cu.hash.storeexercise.utils.BaseClass;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Api(tags = "Login service")
public class LoginController extends BaseClass {

    private final KeycloakResource keycloakResource;

    @PostMapping("/api/v1/login")
    public ResponseEntity<Object> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (description = "User and password", required = true, content =
                    @Content(schema = @Schema(implementation = UserLogin.class)))@RequestBody UserLogin userLogin) throws UserAndPasswordWrongException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> requests = new LinkedMultiValueMap<>();
            requests.add("client_id", keycloakResource.getClientId());
            requests.add("username", userLogin.getUsername());
            requests.add("password", userLogin.getPassword());
            requests.add("grant_type", keycloakResource.getGrantType());
            ResponseEntity<Object> response = restTemplate.postForEntity(keycloakResource.getUrlFormatted(),
                    requests, Object.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                logger.log(Level.DEBUG,userLogin.getUsername()+" " + KeyConstants.CORRECT_LOGIN);
            }

            return response;
        }catch (Exception e){
            throw new UserAndPasswordWrongException(e.getMessage(),userLogin.getUsername());
        }
    }

    @Autowired
    public LoginController(KeycloakResource keycloakResource){
        this.keycloakResource=keycloakResource;
    }

}
