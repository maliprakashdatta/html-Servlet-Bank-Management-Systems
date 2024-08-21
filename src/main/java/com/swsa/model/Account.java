package com.swsa.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    String accountNumber;
    String accountHolderName;
    double balance;
    Customer customer;


    public Account(String accountNumber) {
    }

    public Account(String accountNumber, Customer amount) {
    }

    public Account(String accountNumber, int customerId) {
    }
    public Account(String accountNumber, String accountHolderName, double balance, int customerId) {
    }
    public Account(String accountNumber, double balance, String accountHolderName, int customerId) {
    }

    public void depositMoney(String accountNumber, double amount) {
    }

    public void setCustomer(String customer) {
    }
}













