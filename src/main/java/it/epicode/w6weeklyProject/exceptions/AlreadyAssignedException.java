package it.epicode.w6weeklyProject.exceptions;

public class AlreadyAssignedException extends RuntimeException {
    public AlreadyAssignedException(String message) {
        super(message);
    }
}
