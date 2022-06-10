package cu.hash.storeexercise.exceptions;

import lombok.Getter;

@Getter
public class UserAndPasswordWrongException  extends RuntimeException{

    private String username;

    public UserAndPasswordWrongException(String msg,String username){
        super(msg);
        this.username=username;
    }
}
