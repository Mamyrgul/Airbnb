package java16.dao;

import java16.entities.Customer;
import java16.enums.FamilyStatus;
import java16.enums.Gender;
import java16.enums.HouseType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {
    String createCustomer(Customer customer);

    boolean updateCustomer(Long id,Customer customer);

    Customer getCustomer(Long id);

    List<Customer> getCustomers();

    void createCustomerWithRent( String firstName, String lastName, String email, LocalDate birthDate,
                                 Gender gender, String nationality, FamilyStatus familyStatus,
                                 Long houseId, LocalDate rentStartDate, LocalDate rentEndDate);
    void arendHouse(Long customerId, Long agencyId, LocalDate checkIn, LocalDate checkOut);

    boolean deleteCustomer(Long customerId);
}
