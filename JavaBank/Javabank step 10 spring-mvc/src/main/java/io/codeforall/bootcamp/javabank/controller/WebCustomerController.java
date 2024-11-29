package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET,value = "/customer")
public class WebCustomerController {

    private CustomerServiceImpl customerService;

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public String getCustomers(Model model) {
        List<Integer> customerIds = customerService.getCustomerIds();

        List<Customer> customers = new java.util.ArrayList<>();

        for (Integer customerId : customerIds) {
            customers.add(customerService.get(customerId));
        }

        model.addAttribute("customers", customers);

        //TODO: PUT CUSTOMERS IN CUSTOMER LIST WHEN HTML IS CREATED
        return "customerList";
    }


}
