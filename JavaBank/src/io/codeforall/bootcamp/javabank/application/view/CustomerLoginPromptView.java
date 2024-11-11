package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

public class CustomerLoginPromptView extends AbstractPromptView {
    private final IntegerSetInputScanner scanner;
    private static final String message = Messages.CHOOSE_CUSTOMER;
    private static final String error = Messages.ERROR_INVALID_CUSTOMER;

    public CustomerLoginPromptView(Bank bank) {
        super(bank);
        this.scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(message);
        scanner.setError(error);
    }

    @Override
    public void show() {
        int customerId = getPrompt().getUserInput(scanner);
        getController().setCustomerId(customerId);
    }
}
