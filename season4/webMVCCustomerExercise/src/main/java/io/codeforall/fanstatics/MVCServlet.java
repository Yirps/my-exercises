package io.codeforall.fanstatics;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MVCServlet extends HttpServlet {

    public Customer createCustomer() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Tobias");
        customer.setEmail("tobias@codeforall");
        customer.setPhoneNumber(123456);
        return customer;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = createCustomer();
        RequestDispatcher page1Dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/ClientView.jsp");





        req.getSession().setAttribute("customer", customer);

        page1Dispatcher.forward(req, resp);

        resp.getWriter().println(page1Dispatcher);

        resp.getWriter().close();
    }
}
