package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcCustomerService;

import java.sql.*;
import java.util.List;

public class JDBCCustomerDao implements CustomerDao {

    JDBCTransactionManager tm;
    JDBCSessionManager sm = new JDBCSessionManager();
    JdbcCustomerService cs = new JdbcCustomerService(new JDBCSessionManager());

    @Override
    public Customer get(Integer id) {
/*
        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, customer.version AS cVersion, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id " +
                    "WHERE customer.id = ?";

            PreparedStatement statement = sm.getCurrentConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (cs.get(id) == null) {
                    buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");
                Account account = accountService.get(accountId);

                if (account == null) {
                    break;
                }

                cs.addAccount(account);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public List<Customer> list() {
        return null;
    }

    @Override
    public void add(Customer customer) {

        try {

            Connection connection = sm.getCurrentConnection();

            String query = "INSERT INTO customer(first_name, last_name, email, phone) " +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            tm.rollback();
            e.printStackTrace();
        }
    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setVersion(resultSet.getInt("cVersion"));

        return customer;
    }
}
