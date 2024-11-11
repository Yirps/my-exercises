package io.codeforall.bootcamp.javabank.view;

import io.codeforall.bootcamp.javabank.controller.BalanceController;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A view used to show the customer balance
 *
 * @see BalanceController
 */
public class BalanceView implements View {

    private DecimalFormat df = new DecimalFormat("#.##");
    private BalanceController balanceController;

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param balanceController the controller to set
     */
    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        Customer customer = balanceController.getCustomer();
        System.out.println("\n" + customer.getName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(balanceController.getCustomerBalance()));
    }
}
