package org.inspector4j;

public class Inspect4JException extends RuntimeException {

    public Inspect4JException() {
    }

    public Inspect4JException(String message) {
        super(message);
    }

    public Inspect4JException(String message, Throwable cause) {
        super(message, cause);
    }

    public Inspect4JException(Throwable cause) {
        super(cause);
    }

}
