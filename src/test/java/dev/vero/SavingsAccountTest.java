package dev.vero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount activeAccount;
    private SavingsAccount inactiveAccount;

    @BeforeEach
    void setUp() {
        activeAccount = new SavingsAccount(15000, 0.05f);
        inactiveAccount = new SavingsAccount(5000, 0.05f);
    }

    @Test
    void testInitialState() {
        assertTrue(activeAccount.isActive());
        assertFalse(inactiveAccount.isActive());
    }

    @Test
    void testDeposit() {
        activeAccount.deposit(1000);
        assertEquals(16000, activeAccount.getBalance());

        inactiveAccount.deposit(1000);
        assertEquals(5000, inactiveAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        activeAccount.withdraw(1000);
        assertEquals(14000, activeAccount.getBalance());

        inactiveAccount.withdraw(1000);
        assertEquals(5000, inactiveAccount.getBalance());
    }

    // @Test
    // void testUpdateAccountStatus() {
    //     activeAccount.withdraw(6000);
    //     assertFalse(activeAccount.isActive());

    //     inactiveAccount.deposit(6000);
    //     assertTrue(inactiveAccount.isActive());
    // }

    @Test
    void testGenerateMonthlyStatement() {
        for (int i = 0; i < 5; i++) {
            activeAccount.withdraw(100);
        }
        float initialBalance = activeAccount.getBalance();
        activeAccount.generateMonthlyStatement();
        float finalBalance = activeAccount.getBalance();
        
        assertTrue(finalBalance < initialBalance - 1000);
    }
}