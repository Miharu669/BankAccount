package dev.vero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

    private CheckingAccount checkingAccount;

    @BeforeEach
    public void setUp() {
        checkingAccount = new CheckingAccount(1000.0f, 0.05f);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(1000.0f, checkingAccount.balance, "Initial balance should be $1000");
    }

    @Test
    public void testDeposit() {
        checkingAccount.deposit(500.0f);
        assertEquals(1500.0f, checkingAccount.balance, "Balance should be $1500 after depositing $500");
        assertEquals(1, checkingAccount.numDeposits, "Number of deposits should be 1");
    }

    @Test
    public void testWithdrawWithoutOverdraft() {
        checkingAccount.withdraw(200.0f);
        assertEquals(800.0f, checkingAccount.balance, "Balance should be $800 after withdrawing $200");
        assertEquals(1, checkingAccount.numWithdrawals, "Number of withdrawals should be 1");
        assertEquals(0.0f, checkingAccount.overdraft, "Overdraft should be $0");
    }

    @Test
    public void testGenerateMonthlyStatement() {
        checkingAccount.deposit(500.0f);
        checkingAccount.generateMonthlyStatement();

        float expectedInterest = (0.05f / 12) * 1500.0f;
        float expectedBalance = 1500.0f + expectedInterest;

        assertEquals(expectedBalance, checkingAccount.balance, 0.01f, "Balance should reflect interest");
        assertEquals(0.0f, checkingAccount.overdraft, "Overdraft should be $0 at the end of the month");
    }
}