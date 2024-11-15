package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.TransactionManager;

import java.sql.SQLException;

public class JDBCTransactionManager implements TransactionManager {

    private JDBCSessionManager sm;
    @Override
    public void beginRead() {
         sm.startSession();
    }

    @Override
    public void beginWrite() {
        try {
            sm.getCurrentConnection().setAutoCommit(false);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            if (!sm.getCurrentConnection().getAutoCommit()) {
                sm.getCurrentConnection().commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        sm.stopSession();
    }

    @Override
    public void rollback() {
        try {
            if (!sm.getCurrentConnection().getAutoCommit()) {
                sm.getCurrentConnection().rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        sm.stopSession();
    }
}
