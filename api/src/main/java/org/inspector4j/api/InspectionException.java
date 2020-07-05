package org.inspector4j.api;

public class InspectionException extends InspectorException {

    public InspectionException() {
    }

    public InspectionException(String message) {
        super(message);
    }

    public InspectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InspectionException(Throwable cause) {
        super(cause);
    }

}
