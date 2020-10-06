package org.inspector4j.api.internal;

import org.inspector4j.Inspect4JException;

public class InspectorException extends Inspect4JException {
    public InspectorException() {
    }

    public InspectorException(String message) {
        super(message);
    }

    public InspectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InspectorException(Throwable cause) {
        super(cause);
    }
}
