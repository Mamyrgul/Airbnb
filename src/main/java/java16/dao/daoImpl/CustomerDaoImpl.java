package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java16.config.HibernateConfig;
import java16.dao.CustomerDao;
import java16.entities.Agency;
import java16.entities.Customer;
import java16.entities.House;
import java16.entities.Rent_Info;
import java16.enums.FamilyStatus;
import java16.enums.Gender;
import java16.enums.HouseType;

import java.time.LocalDate;
import java.util.List;


public class CustomerDaoImpl implements CustomerDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String createCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return "Customer created";
    }

    @Override
    public boolean updateCustomer(Long id,Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }return true;
    }

    @Override
    public Customer getCustomer(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }return null;
    }

    @Override
    public List<Customer> getCustomers() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("from Customer").getResultList();
            entityManager.getTransaction().commit();
            return customers;
        }catch (Exception e) {
            e.printStackTrace();
        }return null;
    }

    public void createCustomerWithRent(
            String firstName, String lastName, String email, LocalDate birthDate,
            Gender gender, String nationality, FamilyStatus familyStatus,
            Long houseId, LocalDate rentStartDate, LocalDate rentEndDate) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            House house = entityManager.find(House.class, houseId);
            if (house == null) {
                throw new IllegalArgumentException("House with ID " + houseId + " not found.");
            }

            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setBirthDate(birthDate);
            customer.setGender(gender);
            customer.setNationality(nationality);
            customer.setFamilyStatus(familyStatus);
            entityManager.persist(customer);

            Rent_Info rentInfo = new Rent_Info();
            rentInfo.setCustomer(customer);
            rentInfo.setCheckIn(rentStartDate);
            rentInfo.setCheckOut(rentEndDate);

            entityManager.persist(rentInfo);

            entityManager.getTransaction().commit();
            System.out.println("Customer with rent info successfully created.");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error creating customer with rent info: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
    @Override
    public void arendHouse(Long customerId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Customer customer = entityManager.find(Customer.class, customerId);
            if (customer == null) {
                throw new IllegalArgumentException("Customer with ID " + customerId + " not found.");
            }

            Agency agency = entityManager.find(Agency.class, agencyId);
            if (agency == null) {
                throw new IllegalArgumentException("Agency with ID " + agencyId + " not found.");
            }

            Rent_Info rentInfo = new Rent_Info();
            rentInfo.setCustomer(customer);
            rentInfo.setAgency(agency);
            rentInfo.setCheckIn(checkIn);
            rentInfo.setCheckOut(checkOut);

            entityManager.persist(rentInfo);

            entityManager.getTransaction().commit();
            System.out.println("House rented successfully!");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Customer customer = entityManager.find(Customer.class, customerId);
            if (customer == null) {
                System.out.println("Customer not found");
                return false;
            }

            if (customer.getRentInfos().isEmpty()) {
                entityManager.remove(customer);
            } else {

                boolean allRemoved = true;
                LocalDate currentDate = LocalDate.now();
                for (Rent_Info rentInfo : customer.getRentInfos()) {
                    if (rentInfo.getCheckOut().isBefore(currentDate)) {
                        entityManager.remove(rentInfo);
                    } else {

                        allRemoved = false;
                    }
                }

                if (allRemoved) {
                    entityManager.remove(customer);
                } else {
                    System.out.println("Cannot delete customer — active rentals exist.");
                    entityManager.getTransaction().rollback();
                    return false;
                }
            }
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
