import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BankTest {
    private Customer customer;
    private static final double INITIAL_BALANCE = 1000.00;

    @BeforeEach
    void setUp() {
        customer = new Customer("Test User", "1234");
        customer.getCheckingAccount().deposit(INITIAL_BALANCE);
        customer.getSavingsAccount().deposit(INITIAL_BALANCE);
        customer.getCreditAccount().setBalance(INITIAL_BALANCE);
    }

    @Test
    void testInquireBalanceChecking() {
        assertEquals(INITIAL_BALANCE, customer.getCheckingAccount().getBalance());
    }

    @Test
    void testInquireBalanceSavings() {
        assertEquals(INITIAL_BALANCE, customer.getSavingsAccount().getBalance());
    }

    @Test
    void testInquireBalanceCredit() {
        assertEquals(INITIAL_BALANCE, customer.getCreditAccount().getBalance());
    }
}
