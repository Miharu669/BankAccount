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
    //falla
public void testDeposit() {
    SavingsAccount account = new SavingsAccount(4000, 5);
    account.deposit(1000); 
    assertEquals(5000.0, account.getBalance(), 0.01); 
}


    @Test
    void testWithdraw() {
        activeAccount.withdraw(1000);
        assertEquals(14000, activeAccount.getBalance());

        inactiveAccount.withdraw(1000);
        assertEquals(5000, inactiveAccount.getBalance());
    }

    @Test
    //falla
    void testUpdateAccountStatus() {
        activeAccount.withdraw(6000);
        assertFalse(activeAccount.isActive());

        inactiveAccount.deposit(6000);
        assertTrue(inactiveAccount.isActive());
    }

    @Test
    //falla
public void testGenerateMonthlyStatement() {
    SavingsAccount account = new SavingsAccount(15000, 5);
    account.deposit(1000); 
    account.withdraw(500); 
    account.generateMonthlyStatement();
    assertEquals(13500.0, account.getBalance(), 0.01); 
}

}