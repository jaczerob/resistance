package dev.jaczerob.resistance.api.exceptions;

public abstract class ResistanceException extends Exception {
    public ResistanceException() {
        super();
    }

    public ResistanceException(final String message) {
        super(message);
    }

    public ResistanceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResistanceException(final Throwable cause) {
        super(cause);
    }
}
