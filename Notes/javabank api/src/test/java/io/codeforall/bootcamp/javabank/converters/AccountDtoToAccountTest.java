package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.command.AccountDto;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.model.account.CheckingAccount;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class AccountDtoToAccountTest {

    private AccountDtoToAccount accountDtoToAccount;
    private CustomerService customerService;
    private AccountFactory accountFactory;

    @Before
    public void setup() {
        accountDtoToAccount = new AccountDtoToAccount();
        customerService = mock(CustomerService.class);
        accountFactory = mock(AccountFactory.class);

        accountDtoToAccount.setAccountFactory(accountFactory);
    }

    @Test
    public void testConvert() {

        //setup
        Double fakeInitialAmount = 1000.00;
        AccountType accountType = AccountType.CHECKING;
        Account fakeAccount = spy(CheckingAccount.class);
        AccountDto fakeAccountDto = mock(AccountDto.class);

        when(accountFactory.createAccount(accountType)).thenReturn(fakeAccount);
        when(fakeAccountDto.getBalance()).thenReturn(fakeInitialAmount.toString());
        when(fakeAccountDto.getType()).thenReturn(accountType);

        //exercise
        Account account = accountDtoToAccount.convert(fakeAccountDto);

        //verify
        verify(accountFactory, times(1)).createAccount(accountType);
        verify(fakeAccountDto, times(1)).getType();
        verify(fakeAccountDto, times(2)).getBalance();
        verify(fakeAccount, times(1)).credit(fakeInitialAmount);

        assertTrue(account.getAccountType() == fakeAccountDto.getType());
        assertTrue(account.getBalance() == fakeInitialAmount);
    }
}
