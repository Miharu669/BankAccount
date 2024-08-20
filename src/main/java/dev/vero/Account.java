package dev.vero;

public class Account {
    protected float balance;
    protected int numDeposits = 0;
    protected int numWithdrawals = 0;
    protected float annualInterestRate;
    protected float monthlyFee = 0;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public void deposit(float amount) {
        balance += amount;
        numDeposits++;
    }

    public void withdraw(float amount) {
        if (amount < 0) {
            System.out.println("Cannot withdraw a negative amount.");
            return;
        }
        float newBalance = balance - amount;
        if (newBalance >= 0) {
            balance -= amount;
            numWithdrawals++;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void calculateMonthlyInterest() {
        float monthlyInterestRate = annualInterestRate / 12;
        float monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
    }

    public void generateMonthlyStatement() {
        calculateMonthlyInterest();
        balance -= monthlyFee;
        balance = round(balance, 2);
    }

    public float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places cannot be negative.");
        float scale = (float) Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void printAccountDetails() {
        System.out.println("Balance: " + balance);
        System.out.println("Number of Deposits: " + numDeposits);
        System.out.println("Number of Withdrawals: " + numWithdrawals);
        System.out.println("Annual Interest Rate: " + annualInterestRate + "%");
        System.out.println("Monthly Fee: " + monthlyFee);
    }
}
