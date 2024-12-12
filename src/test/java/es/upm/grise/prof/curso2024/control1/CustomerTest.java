package es.upm.grise.prof.curso2024.control1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
    void getAccountWithHighestBalanceExceptionTest() {
        Customer customer = new Customer();
        Assertions.assertThrows(NoAccountsException.class, () -> {
            customer.getAccountWithHighestBalance();
        });
    }
	
    private static final String ACCOUNT_NUMBER_1 = "ACC-001";
    private static final String ACCOUNT_NUMBER_2 = "ACC-002";
    private static final float INITIAL_AMOUNT_1 = 100.0f;
    private static final float INITIAL_AMOUNT_2 = 200.0f;

    @Test
    void getAccountWithHighestBalanceAccountNumberTest() {
        Customer customer = new Customer();

        Account account1 = new Account();
        account1.setAccountNumber(ACCOUNT_NUMBER_1);
        account1.setInitialAmount(INITIAL_AMOUNT_1);

        Account account2 = new Account();
        account2.setAccountNumber(ACCOUNT_NUMBER_2);
        account2.setInitialAmount(INITIAL_AMOUNT_2);

        customer.addAccount(account1);
        customer.addAccount(account2);

        String highestBalanceAccountNumber = null;
		try {
			highestBalanceAccountNumber = customer.getAccountWithHighestBalance();
		} catch (NoAccountsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertEquals(ACCOUNT_NUMBER_2, highestBalanceAccountNumber, 
            "Se esperaba la cuenta con número " + ACCOUNT_NUMBER_2 + " al tener mayor saldo.");
    }
}
