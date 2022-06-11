package cu.hash.storeexercise.controllers;

import cu.hash.storeexercise.exceptions.ItemAlreadyExistException;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
import cu.hash.storeexercise.exceptions.NotValidFieldsException;
import cu.hash.storeexercise.utils.BaseClass;
import cu.hash.storeexercise.utils.ResponseException;
import cu.hash.storeexercise.exceptions.UserAndPasswordWrongException;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ControllerAdvice
public class ControllerExceptionHandler extends BaseClass {

    @ExceptionHandler(UserAndPasswordWrongException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseException exceptionHandlerWrongUserAndPassword(UserAndPasswordWrongException exception){
        return this.creationResponseException(exception,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseException exceptionHandlerItemAlreadyException(ItemAlreadyExistException exception){
        return this.creationResponseException(exception,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundItemException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException exceptionHandlerNotFoundItemException(NotFoundItemException exception){
        return this.creationResponseException(exception,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseException exceptionMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return this.creationResponseException(exception,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotValidFieldsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseException exceptionHandlerNotValidFieldItemException(NotValidFieldsException exception){
        return this.creationResponseException(exception,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseException exceptionHandlerException(Exception exception){
        return this.creationResponseException(exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseException creationResponseException(RuntimeException exception,HttpStatus status){
        String codeFailure = UUID.randomUUID().toString();
        ResponseException responseException = new ResponseException();
        responseException.setMessage(exception.getMessage());
        responseException.setCode(codeFailure);
        responseException.setHttpStatus(status);
        responseException.setBackendMessage(exception.getMessage());
        logger.log(Level.ERROR, responseException.getHttpStatus()+" "+responseException.getCode()+" "+responseException.getMessage());
        return responseException;
    }

    private ResponseException creationResponseException(Exception exception,HttpStatus status){
        String codeFailure = UUID.randomUUID().toString();
        ResponseException responseException = new ResponseException();
        responseException.setMessage(exception.getMessage());
        responseException.setCode(codeFailure);
        responseException.setHttpStatus(status);
        responseException.setBackendMessage(exception.getMessage());
        logger.log(Level.ERROR, responseException.getHttpStatus()+" "+responseException.getCode()+" "+responseException.getMessage());
        return responseException;
    }

}
