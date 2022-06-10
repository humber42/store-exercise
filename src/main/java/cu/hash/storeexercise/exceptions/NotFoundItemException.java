package cu.hash.storeexercise.exceptions;

public class NotFoundItemException extends RuntimeException{
    public NotFoundItemException(String msg){
        super(msg);
    }
}
