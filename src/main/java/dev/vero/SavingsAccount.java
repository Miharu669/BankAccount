package dev.vero;

public class SavingsAccount extends Account {
    private boolean isActive;

    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.isActive = balance >= 10000;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void deposit(float amount) {

        super.deposit(amount);
        updateAccountStatus();

    }

    @Override
    public void withdraw(float amount) {
        
            if (isActive) {
                
                    super.withdraw(amount);
               
            } else {
                System.out.println("Account is inactive. Cannot withdraw.");
            }
        this.updateAccountStatus();
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

    @Override
    public void printAccountDetails() {
        System.out.println("Balance: $" + balance);
        super.printAccountDetails();
        System.out.println("Account Active: " + isActive);
    }
}
