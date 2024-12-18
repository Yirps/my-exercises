package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.TransactionException;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;

import java.util.Optional;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionManager tx;

    /**
     * Sets the account data access object
     *
     * @param accountDao the account DAO to set
     */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Sets the transaction manager
     *
     * @param tx the transaction manager to set
     */
    public void setTransactionManager(TransactionManager tx) {
        this.tx = tx;
    }

    /**
     * @see AccountService#get(Integer)
     */
    @Override
    public Account get(Integer id) {

        try {

            tx.beginRead();
            return accountDao.findById(id);

        } finally {
            tx.commit();
        }
    }

    /**
     * @see AccountService#add(Account)
     */
    @Override
    public Integer add(Account account) {

        Integer id = null;

        try {

            tx.beginWrite();

            id = accountDao.saveOrUpdate(account).getId();

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }

        return id;
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    @Override
    public void deposit(Integer id, double amount) {

        try {

            tx.beginWrite();

            Optional<Account> account = Optional.ofNullable(accountDao.findById(id));

            if (!account.isPresent()) {
                tx.rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id"))
                    .credit(amount);

            accountDao.saveOrUpdate(account.get());

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        try {

            tx.beginWrite();

            Optional<Account> account = Optional.ofNullable(accountDao.findById(id));

            if (!account.isPresent()) {
                tx.rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id"))
                    .debit(amount);

            accountDao.saveOrUpdate(account.get());

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {

            tx.beginWrite();

            Optional<Account> srcAccount = Optional.ofNullable(accountDao.findById(srcId));
            Optional<Account> dstAccount = Optional.ofNullable(accountDao.findById(dstId));

            if (!srcAccount.isPresent() || !dstAccount.isPresent()) {
                tx.rollback();
            }

            srcAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));
            dstAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));

            if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
                srcAccount.get().debit(amount);
                dstAccount.get().credit(amount);
            }

            accountDao.saveOrUpdate(srcAccount.get());
            accountDao.saveOrUpdate(dstAccount.get());

            tx.commit();

        } catch (TransactionException ex) {

            tx.rollback();
        }
    }
}


