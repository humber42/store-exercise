package cu.hash.storeexercise.exceptions;

import lombok.Getter;

@Getter
public class UserAndPasswordWrongException  extends RuntimeException{

    private final String username;

    public UserAndPasswordWrongException(String msg,String username){
        super(msg);
        this.username=username;
    }
}
