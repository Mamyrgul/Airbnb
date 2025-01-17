package java16.dao;

import java16.entities.Customer;

import java.util.List;

public interface CustomerDao {
    String createCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Long id);
    Customer getCustomer(Long id);
    List<Customer> getCustomers();

}
