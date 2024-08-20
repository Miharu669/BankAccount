package dev.vero;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDeposit() {

        Account account = new Account(1000.0f, 0.05f);

        account.deposit(500.0f);

        assertEquals(1500.0f, account.balance, "Deposit should increase the balance.");
        assertEquals(1, account.numDeposits, "Number of deposits should be incremented.");
    }

    @Test
    public void testWithdraw() {

        Account account = new Account(1000.0f, 0.05f);

        account.withdraw(200.0f);

        assertEquals(800.0f, account.balance, "Withdraw should decrease the balance.");
        assertEquals(1, account.numWithdrawals, "Number of withdrawals should be incremented.");
    }

    @Test
    public void testWithdrawNegativeAmount() {

        Account account = new Account(1000.0f, 0.05f);

        account.withdraw(-200.0f);

        assertEquals(1000.0f, account.balance, "Withdraw should not affect the balance if the amount is negative.");
    }

    @Test
    public void testCalculateMonthlyInterest() {
        Account account = new Account(1000.0f, 0.12f);
        account.calculateMonthlyInterest();

        assertEquals(1010.0f, account.balance, "Monthly interest calculation should be correct.");
    }

    @Test
    public void testRound() {
        Account account = new Account(1000.0f, 0.12f);

        float roundedValue = account.round(1000.12345f, 2);

        assertEquals(1000.12f, roundedValue, 0.01, "Rounding should work correctly.");
    }

    @Test
    public void testGenerateMonthlyStatement() {
        Account account = new Account(1000.0f, 0.12f);
        account.monthlyFee = 10.0f;

        account.generateMonthlyStatement();

        float expectedBalance = 1000.0f - 10.0f + (1000.0f * 0.12f / 12);

        assertEquals(expectedBalance, account.balance, 0.01,
                "Monthly statement should apply fee and interest correctly.");
    }

}
