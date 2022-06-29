package cu.hash.storeexercise.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseException extends Exception {

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
    private final String backendMessage;


    public ResponseException(HttpStatus httpStatus, String message, String code, String backendMessage) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
        this.backendMessage = backendMessage;
    }
}
