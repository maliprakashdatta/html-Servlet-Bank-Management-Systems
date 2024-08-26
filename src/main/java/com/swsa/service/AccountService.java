package com.swsa.service;
import com.swsa.model.Account;
import com.swsa.model.Customer;
import com.swsa.repository.AccountRepository;
import com.swsa.repository.CustomerRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountService {

    private static final AccountRepository ACCOUNT_REPOSITORY = new AccountRepository();
    private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();

    //====================Account ===========================
    public boolean insertAccount(Account account)throws SQLException {
        if (ACCOUNT_REPOSITORY.insertAccount(account)) {
            System.out.println("Account inserted successfully!");
        } else {
            System.out.println("Failed to insert Account.");
            return false;
        }
        return true;

    }
//
//    Customer customer;
//
//    {
//        customer = CUSTOMER_REPOSITORY.retrieveCustomer(customer.getCustomerId());
//    }

    public List<Account> retrieveAccount() {
        return (List<Account>) ACCOUNT_REPOSITORY.retrieveAccount();
    }

    //==================Deposit================================

    protected boolean insertdepositmoney(Account account)throws SQLException {
        if (ACCOUNT_REPOSITORY.insertdepositMoney(account)) {
            System.out.println("deposit inserted successfully!");
        } else {
            System.out.println("Failed to insert deposit.");
            return false;
        }
        return true;

    }

/*

//===================Withdraw Amount =================================

    protected boolean insertwithdrawMoney(Account account)throws SQLException {
        if (ACCOUNT_REPOSITORY.insertwithdrawMoney(account)) {
            System.out.println("withdraw Money inserted successfully!");
        } else {
            System.out.println("Failed to  withdraw Money.");
            return false;
        }
        return true;

    }


    public double checkBalance () {
        return 0;
    }


//================get Balance ==============================
    public void getBalance()

    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Pls enter Customer ID:");
        int customerId = Integer.parseInt(scanner1.nextLine());
        scanner.nextLine();
        Customer customer = CUSTOMER_REPOSITORY.retrieveCustomer(customerId);
        Account account3 = new Account(accountNumber, customer);

        try {
            if (ACCOUNT_REPOSITORY.getBalance(account3)) {
                System.out.println("  Balance Is:"+account3);
            } else {
                System.out.println("Not Checked   .");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
*/

}
















