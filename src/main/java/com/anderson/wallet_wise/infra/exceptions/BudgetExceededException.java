package com.anderson.wallet_wise.infra.exceptions;

public class BudgetExceededException extends RuntimeException {
    public BudgetExceededException(String message) {
        super(message);
    }
}
