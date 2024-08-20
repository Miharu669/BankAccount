package dev.vero;

public class CheckingAccount extends Account {
    public float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        if (amount > 0) {
            if (amount <= balance + overdraft) {
                if (amount <= balance) {
                    balance -= amount;
                } else {
                    overdraft = amount - balance;
                    balance = 0;
                }
                numWithdrawals++;
            } else {
                System.out.println("Insufficient funds including overdraft.");
            }
        } else {
            System.out.println("Cannot withdraw a negative amount.");
        }
    }

    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            if (overdraft > 0) {
                if (amount > overdraft) {
                    balance = amount - overdraft;
                    overdraft = 0;
                } else {
                    overdraft -= amount;
                }
            } else {
                balance += amount;
            }
            numDeposits++;
        } else {
            System.out.println("Cannot deposit a negative amount.");
        }
    }

    @Override
    public void generateMonthlyStatement() {
        super.generateMonthlyStatement();
        if (balance < 0) {
            overdraft = -balance;
            balance = 0;
        }
    }

    @Override
    public void printAccountDetails() {
        System.out.println("Balance: $" + balance);
        System.out.println("Overdraft: $" + overdraft);
        super.printAccountDetails();
    }
}