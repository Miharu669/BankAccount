package dev.vero;

public class CheckingAccount extends Account {
    float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        float result = balance - amount;
        if (result < 0) {
            overdraft = overdraft - result;
            balance = 0;
        } else {
            super.withdraw(amount);
        }
    }

    @Override
    public void deposit(float amount) {
        if (overdraft > 0) {
            if (amount >= overdraft) {
                balance += (amount - overdraft);
                overdraft = 0;
            } else {
                overdraft -= amount;
            }
        } else {
            super.deposit(amount);
        }
    }

    @Override
    public void generateMonthlyStatement() {
        super.generateMonthlyStatement();
    }

    @Override
    public void printAccountDetails() {
        super.printAccountDetails();
        System.out.println("Overdraft: " + overdraft);
    }
}
