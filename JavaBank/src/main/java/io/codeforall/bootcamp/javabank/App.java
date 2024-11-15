package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcAccountService;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcCustomerService;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
        JdbcCustomerService cs = new JdbcCustomerService(new JDBCSessionManager());

        Customer customer = new Customer();
        customer.setEmail("akjshdkhjasd");
        customer.setFirstName("Batata");
        customer.setLastName("Frita");
        cs.add(customer);

    }

    private void bootStrap() {

        JDBCSessionManager jdbcSessionManager = new JDBCSessionManager();

        AccountFactory accountFactory = new AccountFactory();
        JdbcAccountService accountService = new JdbcAccountService(jdbcSessionManager, accountFactory);
        JdbcCustomerService customerService = new JdbcCustomerService(jdbcSessionManager);
        customerService.setAccountService(accountService);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        bootstrap.setAccountFactory(accountFactory);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        jdbcSessionManager.stopSession();
    }
}
