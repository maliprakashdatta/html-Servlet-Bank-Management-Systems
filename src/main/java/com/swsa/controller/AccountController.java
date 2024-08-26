package com.swsa.controller;
import com.swsa.model.Account;
import com.swsa.service.AccountService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class AccountController extends HttpServlet {

    private AccountService accountService = new AccountService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        List<Account> accountList = new ArrayList<>();
        accountList = accountService.retrieveAccount();
       /* try {
            accountList =accountService.retrieveAccount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Account  Details</h1>");
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>accountNumber</th>");
        out.println("<th>accountHolderName</th>");
        out.println("<th>balance</th>");
        out.println("<th>customerId</th>");
        out.println("</tr>");

        accountList.parallelStream().forEach(account -> {
            out.println("<tr>");
            out.println("<td>" + account.getAccountNumber() + "</td>");
            out.println("<td>" + account.getAccountHolderName() + "</td>");
            out.println("<td>" + account.getBalance() + "</td>");
            out.println("<td>" + account.getCustomerId() + "</td>");
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("</body></html>");
    }


    //===================Account insert========================================
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        String accountNumber = request.getParameter("accountNumber");
        String accountHolderName = request.getParameter("accountHolderName");
        String balance = request.getParameter("balance");
        String customerId = request.getParameter("customerId");

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setBalance(Double.parseDouble(balance));
        account.getCustomerId(customerId);


        try {
            boolean isInserted = accountService.insertAccount(account);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            if (isInserted) {
                out.println("<h1> Account object inserted to db</h1>");
            } else {
                out.println("<h1> Account  object in NOT inserted to db</h1>");
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


}

