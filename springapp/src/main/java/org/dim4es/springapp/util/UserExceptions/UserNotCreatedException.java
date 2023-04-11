package org.dim4es.springapp.util.UserExceptions;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
