package com.example.bankapp.exception;

public final class BusinessServiceOperationException {

    private BusinessServiceOperationException() {
    }


    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }
    public static class UserNotFoundException extends BaseException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    public static class DrawingAccountExistsException extends BaseException {
        public DrawingAccountExistsException(String message) {
            super(message);
        }
    }


}
