package com.sriwin.automation.exceptions;

public class CoreException extends Exception {
    public CoreException() {
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

    public String toString() {
        String message = getMessage();
        return (message != null) ? (message) : "";
    }
}