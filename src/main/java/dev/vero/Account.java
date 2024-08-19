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
        if (amount <= balance) {
            balance -= amount;
            numWithdrawals++;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void calculateMonthlyInterest() {
        float monthlyInterestRate = annualInterestRate / 12;
        balance += balance * (monthlyInterestRate / 100);
    }

    public void generateMonthlyStatement() {
        balance -= monthlyFee;

        balance = round(balance, 2);
        calculateMonthlyInterest();
    }

    private float round(float value, int places) {
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

    public float getBalance() {
        return balance;
    }

    public int getNumDeposits() {
        return numDeposits;
    }

    public int getNumWithdrawals() {
        return numWithdrawals;
    }

    public float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
