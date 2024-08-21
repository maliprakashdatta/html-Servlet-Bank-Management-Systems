package com.swsa.controller;
import com.swsa.model.Customer;
import com.swsa.service.CustomerService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController extends HttpServlet {

    private CustomerService customerService = new CustomerService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        List<Customer> customerList = new ArrayList<>();
        customerList =customerService.retrieveCustomer();
        try {
            customerList =customerService.retrieveCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Customer Details</h1>");
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>customerId</th>");
        out.println("<th>name</th>");
        out.println("<th>mobileNo</th>");
        out.println("<th>address</th>");
        out.println("<th>emailId</th>");
        out.println("<th>aadhaarNo</th>");
        out.println("</tr>");

        customerList.parallelStream().forEach(customer -> {
            out.println("<tr>");
            out.println("<td>" + customer.getCustomerId()+"</td>");
            out.println("<td>" + customer.getName()+"</td>");
            out.println("<td>" + customer.getMobileNo()+"</td>");
            out.println("<td>" + customer.getAddress() +"</td>");
            out.println("<td>" + customer.getEmailId() +"</td>");
            out.println("<td>" + customer.getAadhaarNo() +"</td>");
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        String customerId= request.getParameter("customerId");
        String name = request.getParameter("name");
        String mobileNo = request.getParameter("mobileNo");
        String address = request.getParameter("address");
        String emailId= request.getParameter("emailId");
        String aadhaarNo=request.getParameter("aadhaarNo");


        Customer customer = new Customer();
        customer.setCustomerId(Integer.parseInt(customerId));
        customer.setName(name);
        customer.setMobileNo(Long.parseLong(mobileNo));
        customer.setAddress(address);
        customer.setEmailId(emailId);
        customer.setAadhaarNo(Long.parseLong(aadhaarNo));


        try {
            boolean isInserted = customerService.insertCustomer(customer);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            if (isInserted) {
                out.println("<h1> Customer object inserted to db</h1>");
            } else {
                out.println("<h1> Customer  object in NOT inserted to db</h1>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the service() method ---------------");
        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else {
            this.doGet(request, response);
        }
    }

    public void destroy() {
        System.out.println("--------------- inside the destroy() method ---------------");
    }
}

