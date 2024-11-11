package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.domain.Bank;

public abstract class AbstractView implements BankView {
    private final Bank bank;

    public AbstractView(Bank bank) {
        this.bank = bank;
    }

    /**
     * Gets the bank model
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    @Override
    public abstract void show();
}
