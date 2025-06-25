package br.com.Movieflix.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
