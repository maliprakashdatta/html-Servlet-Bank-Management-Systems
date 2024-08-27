package com.swsa.service;
import com.swsa.model.Account;
import com.swsa.repository.AccountRepository;

import java.sql.SQLException;
import java.util.List;
public class AccountService {

    private static final AccountRepository ACCOUNT_REPOSITORY = new AccountRepository();
    //====================Account ===========================
//    public boolean insertAccount(Account account)throws SQLException {
//        if (ACCOUNT_REPOSITORY.insertAccount(account)) {
//            System.out.println("Account inserted successfully!");
//        } else {
//            System.out.println("Failed to insert Account.");
//            return false;
//        }
//        return true;
//
//    }

    public List<Account> retrieveAccount() {
        return (List<Account>) ACCOUNT_REPOSITORY.retrieveAccount();
    }

    //==================Deposit================================
//
//    public boolean deposit(Account account)throws SQLException {
//        if (ACCOUNT_REPOSITORY.deposit(account)) {
//            System.out.println("deposit inserted successfully!");
//        } else {
//            System.out.println("Failed to insert deposit.");
//            return false;
//        }
//        return true;
//
//    }

//===================Withdraw Amount =================================

    public boolean withdraw(Account account)throws SQLException {
        if (ACCOUNT_REPOSITORY.withdraw(account)) {
            System.out.println("withdraw Money inserted successfully!");
        } else {
            System.out.println("Failed to  withdraw Money.");
            return false;
        }
        return true;

    }

////
//
////================get Balance ==============================
//    public double getBalance(String accountNumber)
//
//    {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter account number: ");
//        String accountNumber = scanner.nextLine();
//
//        Scanner scanner1 = new Scanner(System.in);
//        System.out.println("Pls enter Customer ID:");
//        int customerId = Integer.parseInt(scanner1.nextLine());
//        scanner.nextLine();
//        Customer customer = CUSTOMER_REPOSITORY.retrieveCustomer(customerId);
//        Account account3 = new Account(accountNumber, customer);
//
//        try {
//            if (ACCOUNT_REPOSITORY.getBalance(account3)) {
//                System.out.println("  Balance Is:"+account3);
//            } else {
//                System.out.println("Not Checked   .");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return 0;
//    }

    public boolean withdraw(String accountNumber, String accountHolderName, double amount, String customerId) {
        return false;
    }

    public boolean insertAccount(String accountNumber, String accountHolderName, String balance, String customerId) {
        return false;
    }

    public boolean deposit(String accountNumber, String accountHolderName, double balance, String customerId) {
        return false;
    }

    public boolean withdraw(String accountNumber, String accountHolderName, String balance, String customerId) {
        return false;
    }

    public boolean insertAccount(Account account) {
        return false;
    }

    public boolean deposit(Account account) {
        return false;
    }
}
















