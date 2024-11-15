package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer get(Integer id);

    public List<Customer> list();

    public void add(Customer customer);


}
