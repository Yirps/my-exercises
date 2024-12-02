package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.DTO.CustomerDTO;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getCustomer(Model model, @PathVariable Integer id) {
        model.addAttribute("customer", customerService.get(id));
        return "customer/customerData";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/form/{id}")
    public String editCustomer(Model model, @PathVariable Integer id){
        Customer customer = customerService.get(id);

        if (customer == null) {
            // Prevent trying to edit customers with an ID that does not exist,
            // redirects to customers list
            return "redirect:/customer/list";
        }

        model.addAttribute(customer);
        return "customer/form";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/update"})
    public String saveCustomer(@ModelAttribute CustomerDTO customer) {
        Customer currentCustomer = customerService.get(customer.getId());

        if (customer.getId() != null) {
            // If user already exists it will edit the following attributes
            currentCustomer = customerService.get(customer.getId());
            currentCustomer.setFirstName(customer.getFirstName());
            currentCustomer.setLastName(customer.getLastName());
            currentCustomer.setEmail(customer.getEmail());
            currentCustomer.setPhone(customer.getPhone());
        }

        // Creates a new customer or updates the existing one
        Customer savedCustomer = customerService.save(currentCustomer);

        // Instead of returning a rendered view to the browser,
        // a 302 redirect is sent to the browser, forcing showCustomer()
        // to execute after adding a new customer
        return "redirect:/customer/" + savedCustomer.getId();
    }


}
