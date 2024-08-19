package dev.vero;

public class CheckingAccount extends Account {
    private float overdraft = 0;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
    }

    @Override
    public void withdraw(float amount) {
        if (amount > balance) {
            overdraft += (amount - balance);
            balance = 0;
        } else {
            super.withdraw(amount);
        }
        numWithdrawals++;
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
