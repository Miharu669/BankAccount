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
            if (amount > 0) {
                super.deposit(amount);
                updateAccountStatus();
            } else {
                System.out.println("Cannot deposit a negative amount.");
            }
        } else {
            System.out.println("Account is inactive. Cannot deposit.");
        }
    }

    @Override
    public void withdraw(float amount) {
        updateAccountStatus();

        if (isActive) {
            if (amount > 0) {
                super.withdraw(amount);
            } else {
                System.out.println("Cannot withdraw a negative amount.");
            }
        } else {
            System.out.println("Account is inactive. Cannot withdraw.");
        }
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
        System.out.println("Balance = $ "+ balance);
        super.printAccountDetails();
        System.out.println("Account Active: " + isActive);
    }
}
