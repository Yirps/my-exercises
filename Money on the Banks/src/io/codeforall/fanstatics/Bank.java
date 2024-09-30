package io.codeforall.fanstatics;

public class Bank {
    private double bankMoney = 5.00;
    private String userName;
    private final int id;
    private String password;
    private String bankName;

    Bank() {
        this.bankMoney = bankMoney;
        this.id = (int) ((Math.random() * 9999) + 1);
        this.bankName = bankName;
    }

    public double getBankMoney(){
        return this.bankMoney;
    }

    public String getBankName(){
        return this.bankName;
    }

    public double depositBankMoney(double value){
        this.bankMoney = this.bankMoney + value;
        return this.bankMoney;
    }

    public double takeBankMoney(double value){
        this.bankMoney = this.bankMoney - value;
        return this.bankMoney;
    }



    public String getUserName() {
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public int getId(){
        return this.id;
    }


}

/*
create a bank that has a user and stores the user money
create the constructor for bank with username, id and money
maybe try to implement a password
 */