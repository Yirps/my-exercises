package io.codeforall.fanstatics;

public class Person {

    Bank bank = new Bank();

    public Person(String userName, String password, String bankName) {
        userName = bank.getUserName();
        password = bank.getPassword();
        bankName = bank.getBankName();
    }

    public String deposit(double value) {
        if(value > 0) {
            bank.depositBankMoney(value);
            return "Deposit successful";
        } else {
            return "Not a valid operation.";
        }
    }

    public String take(double value){
        if(bank.getBankMoney() >= value && value > 0){
            bank.takeBankMoney(value);
            return "Transaction successful.";
        } else if(value <= 0){
            return "Not a valid amount";
        } else {
            return "Ur too poor for that.";
        }
    }

    public double getBalance(){
        return bank.getBankMoney();
    }

    public int getId(){
        return bank.getId();
    }

}