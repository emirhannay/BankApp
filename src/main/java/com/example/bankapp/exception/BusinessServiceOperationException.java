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
    public static class AccountNotFoundException extends BaseException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }
    public static class MoneyTransferFailedException extends BaseException {
        public MoneyTransferFailedException(String message) {
            super(message);
        }
    }
    public static class DeleteCustomerFailedException extends BaseException {
        public DeleteCustomerFailedException(String message) {
            super(message);
        }
    }
    public static class CustomerAlreadyDeletedException extends BaseException {
        public CustomerAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class CreateCustomerFailedException extends BaseException {
        public CreateCustomerFailedException(String message) {
            super(message);
        }
    }




}
