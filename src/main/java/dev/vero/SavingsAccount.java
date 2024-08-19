package dev.vero;

public class SavingsAccount extends Account {
    private boolean isActive;

    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.isActive = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (isActive) {
            super.deposit(amount);
        } else {
            System.out.println("Account is inactive. Cannot deposit.");
        }
        updateAccountStatus();
    }

    @Override
    public void withdraw(float amount) {
        if (isActive) {
            super.withdraw(amount);
        } else {
            System.out.println("Account is inactive. Cannot withdraw.");
        }
        updateAccountStatus();
    }

    @Override
    public void generateMonthlyStatement() {
        if (numWithdrawals > 4) {
            float extraFee = (numWithdrawals - 4) * 1000;
            balance -= extraFee;
            monthlyFee += extraFee;
        }
        super.generateMonthlyStatement();
        updateAccountStatus();
    }

    private void updateAccountStatus() {
        isActive = balance >= 10000;
    }

    public boolean isActive() {
        return isActive;
    }

    public float getBalance() {
        return balance;
    }

    @Override
    public void printAccountDetails() {
        super.printAccountDetails();
        System.out.println("Account Active: " + isActive);
    }
}
