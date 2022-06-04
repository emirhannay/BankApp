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
    public static class CreateCreditCardException extends BaseException {
        public CreateCreditCardException(String message) {
            super(message);
        }
    }
    public static class SavingsAccountNotFoundException extends BaseException {
        public SavingsAccountNotFoundException(String message) {
            super(message);
        }
    }
    public static class DepositMoneyToSavingsAccountFailedException extends BaseException {
        public DepositMoneyToSavingsAccountFailedException(String message) {
            super(message);
        }
    }
    public static class GetCustomerFailedException extends BaseException {
        public GetCustomerFailedException(String message) {
            super(message);
        }
    }public static class CardNotFoundException extends BaseException {
        public CardNotFoundException(String message) {
            super(message);
        }
    }
    public static class PaymentFailedException extends BaseException {
        public PaymentFailedException(String message) {
            super(message);
        }
    }
    public static class CreateAdminFailedException extends BaseException {
        public CreateAdminFailedException(String message) {
            super(message);
        }
    }
    public static class TransferNotFoundException extends BaseException {
        public TransferNotFoundException(String message) {
            super(message);
        }
    }




}
