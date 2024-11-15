package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.account.Account;

public interface AccountDao {

    public Account get(Integer id);

    public void add(Account account);

    public void updateBalance(int id, double totalBalance, int version);
}
