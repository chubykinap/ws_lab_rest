package service.Exceptions;

public class SQLTransactionException extends Exception {
    private static final long serialVersionUID = 0;
    private static SQLTransactionException DEFAULT =
            new SQLTransactionException("An error occurred during transaction execution");

    public SQLTransactionException(String message) {
        super(message);
    }

}
