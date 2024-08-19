package dev.vero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class AccountTest {

    private Account account;
    private SavingsAccount savingsAccount;
    private CheckingAccount checkingAccount;

    @BeforeEach
    public void setUp() {
        account = new Account(10000, 5);
        savingsAccount = new SavingsAccount(12000, 3);
        checkingAccount = new CheckingAccount(2000, 4);
    }

    @Test
    public void testDeposit() {
        account.deposit(2000);
        assertEquals(12000, account.balance, 0.001);
        assertEquals(1, account.numDeposits);
    }

    @Test
    public void testWithdraw() {
        account.withdraw(5000);
        assertEquals(5000, account.balance, 0.001);
        assertEquals(1, account.numWithdrawals);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.withdraw(15000);
        assertEquals(10000, account.balance, 0.001);
        assertEquals(0, account.numWithdrawals);
    }

    @Test
    public void testCalculateMonthlyInterest() {
        account.calculateMonthlyInterest();
        assertEquals(10041.67, account.balance, 0.01);
    }

    @Test
    public void testGenerateMonthlyStatement() {

        Account account = new Account(10000.00f, 5.0f);
        account.setMonthlyFee(500.00f);

        account.generateMonthlyStatement();

        float expectedBalance = 10000.00f - 500.00f;
        expectedBalance += expectedBalance * (5.0f / 12 / 100);

        expectedBalance = Math.round(expectedBalance * 100) / 100.0f;

        assertEquals(expectedBalance, account.getBalance(), 0.01f);
    }

}
