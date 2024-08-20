package dev.vero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {
    private SavingsAccount account;

    @BeforeEach
    public void setUp() {
        account = new SavingsAccount(15000, 0.05f);
    }

    @Test
    public void testDepositActiveAccount() {
        account.deposit(5000);
        assertEquals(20000, account.getBalance(), 0.01);
    }

    @Test
    public void testDepositInactiveAccount() {
        account.withdraw(6000);
        account.deposit(2000);
        assertEquals(11000, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawActiveAccount() {
        account.withdraw(4000);
        assertEquals(11000, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInactiveAccount() {
        account.withdraw(6000);
        assertEquals(9000, account.getBalance(), 0.01);
    }

    @Test
    public void testPrintAccountDetails() {
        assertDoesNotThrow(() -> account.printAccountDetails());
    }
}
