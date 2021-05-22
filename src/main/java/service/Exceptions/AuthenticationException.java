package service.Exceptions;

public class AuthenticationException extends Exception{
    private static final long serialVersionUID = 0;
    public static AuthenticationException DEFAULT =
            new AuthenticationException("Authentication failed");

    public AuthenticationException(String message) {
        super(message);
    }
}
