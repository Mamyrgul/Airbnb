package java16.service.serviceImpl;

import java16.dao.CustomerDao;
import java16.dao.daoImpl.CustomerDaoImpl;
import java16.entities.Customer;
import java16.enums.FamilyStatus;
import java16.enums.Gender;
import java16.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public String createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Long id, Customer customer) {
        return customerDao.updateCustomer(id, customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    public void createCustomerWithRent(String firstName, String lastName, String email, LocalDate birthDate, Gender gender, String nationality, FamilyStatus familyStatus, Long houseId, LocalDate rentStartDate, LocalDate rentEndDate) {
     customerDao.createCustomerWithRent( firstName,lastName, email,  birthDate, gender,nationality, familyStatus,  houseId, rentStartDate, rentEndDate);
    }

    @Override
    public void arendHouse(Long customerId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        customerDao.arendHouse(customerId, agencyId, checkIn, checkOut);
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        return customerDao.deleteCustomer(customerId);
    }
}
