package es.upm.grise.prof.curso2024.control1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class AccountTest {

    private static final String ACCOUNT_NUMBER = "ACC-001";
    private static final float INITIAL_AMOUNT = 100.0f;
    private static final float TRANSACTION_AMOUNT_1 = 50.0f;
    private static final float TRANSACTION_AMOUNT_2 = -10.0f;

    @Test
    void testGetCurrentBalanceReturnsCorrectBalance() {
        Account account = new Account();
        account.setAccountNumber(ACCOUNT_NUMBER);
        account.setInitialAmount(INITIAL_AMOUNT);

        Transaction transaction1 = mock(Transaction.class);
        Transaction transaction2 = mock(Transaction.class);

        when(transaction1.getAmount()).thenReturn(TRANSACTION_AMOUNT_1);
        when(transaction2.getAmount()).thenReturn(TRANSACTION_AMOUNT_2);

        account.addTransaction(transaction1);
        account.addTransaction(transaction2);

        float expectedBalance = INITIAL_AMOUNT + TRANSACTION_AMOUNT_1 + TRANSACTION_AMOUNT_2;

        assertEquals(expectedBalance, account.getCurrentBalance(),
            "El saldo actual no coincide con la suma del saldo inicial y las transacciones");
    }
    
    private static final String ACCOUNT_NUMBER_1 = "ACC-001";
    private static final String ACCOUNT_NUMBER_2 = "ACC-002";
    private static final float BALANCE_1 = 100.0f;
    private static final float BALANCE_2 = 200.0f;
    
    @Test
    void getAccountWithHighestBalanceAccountNumber() throws NoAccountsException {
        Customer customer = new Customer();

        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        when(account1.getAccountNumber()).thenReturn(ACCOUNT_NUMBER_1);
        when(account1.getCurrentBalance()).thenReturn(BALANCE_1);

        when(account2.getAccountNumber()).thenReturn(ACCOUNT_NUMBER_2);
        when(account2.getCurrentBalance()).thenReturn(BALANCE_2);

        customer.addAccount(account1);
        customer.addAccount(account2);

        String highestBalanceAccountNumber = customer.getAccountWithHighestBalance();

        assertEquals(ACCOUNT_NUMBER_2, highestBalanceAccountNumber, 
            "Se esperaba la cuenta con número " + ACCOUNT_NUMBER_2 + " ya que tiene el mayor saldo.");
    }
}
