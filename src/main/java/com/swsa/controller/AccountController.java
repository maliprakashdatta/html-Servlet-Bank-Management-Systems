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
//        try {
//            accountList = accountService.retrieveAccount();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
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
        double balance = Double.parseDouble(request.getParameter("balance"));
        String customerId = request.getParameter("customerId");

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setBalance(balance);
        account.getCustomerId(customerId);
  try {
        // boolean isInserted = accountService.insertAccount(account);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        if (accountService.insertAccount(account)) {
            response.getWriter().println("<p>Account created successfully.</p>");
        } else {
            response.getWriter().println("<p>Account creation failed. Account may already exist.</p>");
        }

        if (accountService.deposit(account))
        {
            response.getWriter().println("<p>deposit money successfully.</p>");
        } else {
            response.getWriter().println("<p>deposit money failed.</p>");
        }

        if (accountService.withdraw(account)) {
            response.getWriter().println("<p>withdraw  money successfully.</p>");
        } else {
            response.getWriter().println("<p>withdraw money failed.</p>");
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


        out.println("</body></html>");
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






//Switch Case


//
//        try {
//            boolean isInserted = accountService.insertAccount(account);
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//
//            switch (action) {
//                case "create":
//                    if (accountService.insertAccount(accountNumber, accountHolderName, balance, customerId)) {
//                        response.getWriter().println("<p>Account created successfully.</p>");
//                    } else {
//                        response.getWriter().println("<p>Account creation failed. Account may already exist.</p>");
//                    }
//                    break;
//
//                case "deposit":
//                    if (accountService.deposit(accountNumber, accountHolderName, balance, customerId)) {
//                        response.getWriter().println("<p>Deposit successful.</p>");
//                    } else {
//                        response.getWriter().println("<p>Deposit failed. Account may not exist.</p>");
//                    }
//                    break;
//
//                case "withdraw":
//                    if (accountService.withdraw(accountNumber, accountHolderName, balance, customerId)) {
//                        response.getWriter().println("<p>Withdrawal successful.</p>");
//                    } else {
//                        response.getWriter().println("<p>Withdrawal failed. Insufficient balance or account may not exist.</p>");
//                    }
//                    break;
//
//                default:
//                    response.getWriter().println("<p>Invalid action.</p>");
//            }
//
//            response.sendRedirect("bankAccount");
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//    }



