package cu.hash.storeexercise.exceptions;

public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException(String msg){
        super(msg);
    }
}
