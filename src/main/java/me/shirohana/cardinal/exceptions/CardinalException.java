package me.shirohana.cardinal.exceptions;

public class CardinalException extends RuntimeException {

    private static final long serialVersionUID = 1372108672600175605L;

    public CardinalException() {
        super();
    }

    public CardinalException(String message) {
        super(message);
    }

    public CardinalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardinalException(Throwable cause) {
        super(cause);
    }

}
