package dev.vero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

    private CheckingAccount checkingAccount;

    @BeforeEach
    public void setUp() {
        checkingAccount = new CheckingAccount(1000.0f, 0.05f); // Initial balance: $1000, Annual Interest Rate: 5%
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
    public void testWithdraw() {
        checkingAccount.withdraw(200.0f);
        assertEquals(800.0f, checkingAccount.balance, "Balance should be $800 after withdrawing $200");
        assertEquals(1, checkingAccount.numWithdrawals, "Number of withdrawals should be 1");
    }

    @Test
    public void testOverdraft() {
        checkingAccount.withdraw(1200.0f);
        assertEquals(0.0f, checkingAccount.balance, "Balance should be $0 after withdrawing $1200");
        assertEquals(200.0f, checkingAccount.overdraft, "Overdraft should be $200");
    }
    @Test
    //Falla
    public void testDepositOverdraft() {

        checkingAccount.withdraw(1200.0f);
        checkingAccount.deposit(1000.0f);

        assertEquals(0.0f, checkingAccount.balance, "Balance should be $0 after depositing $1000");
        assertEquals(0.0f, checkingAccount.overdraft, "Overdraft should be $0 after depositing $1000");
    }

    @Test
    //Falla
    public void testGenerateMonthlyStatement() {

        checkingAccount.deposit(500.0f);
        checkingAccount.generateMonthlyStatement();

        assertEquals(1500.0f, checkingAccount.balance, 0.01,
                "Balance should be $1500 after applying monthly interest and fees");
    }

}
