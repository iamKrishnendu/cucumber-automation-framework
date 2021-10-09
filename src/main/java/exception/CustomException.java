package exception;

/**
 * Custom Exception class<br/>
 * Throw instance of it with user friendly messages<br/>
 */
public class CustomException extends Exception {

    public CustomException(String exception) throws RuntimeException {
        super();
        throw new RuntimeException(exception);
    }

}
