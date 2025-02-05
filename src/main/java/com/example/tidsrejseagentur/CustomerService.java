package com.example.tidsrejseagentur;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(){
        this.customerDAO = new CustomerDAO();
    }

    public boolean createCustomer(String name, String email){
        if(!isValidEmail(email)){
            System.out.println("Ugyldig Email, angiv venligst en korrekt mail!");
        } return false;

        Customer customer = new Customer(name, email);
        return customerDAO.addCustomer(customer);

    }


    public List<Customer> getCustomers(){
        return customerDAO.getCustomers();
    }

    private boolean isValidEmail(String email){
        return email.contains("@");
    }

}
