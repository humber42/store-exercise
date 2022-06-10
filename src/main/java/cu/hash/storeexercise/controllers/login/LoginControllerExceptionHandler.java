package cu.hash.storeexercise.controllers.login;

import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.utils.BaseClass;
import cu.hash.storeexercise.utils.ResponseException;
import cu.hash.storeexercise.exceptions.UserAndPasswordWrongException;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@ControllerAdvice
public class LoginControllerExceptionHandler extends BaseClass {

    @ExceptionHandler(UserAndPasswordWrongException.class)
    @ResponseBody
    ResponseException exceptionHandlerWrongUserAndPassword(UserAndPasswordWrongException exception){
        String codeFailure = UUID.randomUUID().toString();
        ResponseException responseException = new ResponseException();
        responseException.setMessage(KeyConstants.USER_PASSWORD_WRONG);
        responseException.setCode(codeFailure);
        responseException.setHttpStatus(HttpStatus.UNAUTHORIZED);
        responseException.setBackendMessage(exception.getMessage());
        logger.log(Level.INFO,exception.getUsername(),responseException.getMessage());
        return responseException;
    }

}
