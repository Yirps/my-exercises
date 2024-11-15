package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;

public class JDBCAccountDao implements AccountDao {
    @Override
    public Account get(Integer id) {
        return null;
    }

    @Override
    public void add(Account account) {

    }

    @Override
    public void updateBalance(int id, double totalBalance, int version) {

    }
}
