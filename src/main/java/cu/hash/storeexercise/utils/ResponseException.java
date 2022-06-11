package cu.hash.storeexercise.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseException {

    private HttpStatus httpStatus;
    private String message;
    private String code;
    private String backendMessage;

}
