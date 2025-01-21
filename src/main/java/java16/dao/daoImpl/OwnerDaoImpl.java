package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java16.config.HibernateConfig;
import java16.dao.OwnerDao;
import java16.entities.Agency;
import java16.entities.House;
import java16.entities.Owner;
import java16.entities.Rent_Info;
import java16.enums.Gender;
import java16.enums.HouseType;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public String createOwner(Owner owner) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String updateOwner(Long id, Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(owner);
            entityManager.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String createOwnerWithHouse(String firstName, String lastName, String email, LocalDate birthDate, Gender gender, HouseType houseType, int price,
                                       double rating,
                                       String description,
                                       int room,
                                       boolean furniture) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            if (age < 18) {
                return "Error: Owner must be at least 18 years old.";
            }

            entityManager.getTransaction().begin();

            Owner owner = new Owner();
            owner.setFirstName(firstName);
            owner.setLastName(lastName);
            owner.setEmail(email);
            owner.setBirthDate(birthDate);
            owner.setGender(gender);
            entityManager.persist(owner);

            House house = new House();
            house.setHouseType(houseType);
            house.setPrice(price);
            house.setRating(rating);
            house.setRoom(room);
            house.setFurniture(furniture);
            house.setDescription(description);
            house.setOwner(owner);
            entityManager.persist(house);

            entityManager.getTransaction().commit();
            return "Success: Owner and house created successfully.";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error occurred while creating owner and house: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean ownerAssignAgency(Long ownerId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Owner owner = entityManager.find(Owner.class, ownerId);
            if (owner == null) {
                System.out.println("Owner not found.");
                return false;
            }
            Agency agency = entityManager.find(Agency.class, agencyId);
            if (agency == null) {
                System.out.println("Agency not found.");
                return false;
            }

            entityManager.merge(owner);
            entityManager.getTransaction().commit();

            System.out.println("Owner assigned to agency successfully.");
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


    public void deleteOwner(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Owner owner = entityManager.find(Owner.class, ownerId);
            if (owner != null) {

                for (House house : owner.getHouses()) {
                    if (house.getRentInfos() == null || house.getRentInfos().isEmpty()) {

                        entityManager.remove(house);
                    } else {

                        Rent_Info rentInfo = (Rent_Info) house.getRentInfos();
                        if (rentInfo.getCheckOut().isBefore(LocalDate.now())) {

                            entityManager.remove(rentInfo);

                            entityManager.remove(house);
                        } else {
                            System.out.println("House with active rent cannot be deleted.");
                        }
                    }
                }

                entityManager.remove(owner);
            } else {
                System.out.println("Owner not found");
            }

            entityManager.getTransaction().commit();
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
    public Owner getOwner(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Agency agency = entityManager.find(Agency.class, agencyId);

            if (agency == null) {
                throw new RuntimeException("Agency not found with ID: " + agencyId);
            }
            List<Owner> owners = agency.getOwners();
            if (owners == null || owners.isEmpty()) {
                throw new RuntimeException("No owners found for the agency with ID: " + agencyId);
            }

            Owner owner = owners.get(0);

            entityManager.getTransaction().commit();

            return owner;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Map<String, Integer> getOwners() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> owners = entityManager.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
            Map<String, Integer> ownerMap = new HashMap<>();
            for (Owner owner : owners) {
                LocalDate dateOfBirth = owner.getBirthDate();
                if (dateOfBirth != null) {
                    int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
                    ownerMap.put(owner.getLastName(), age);
                }
            }

            entityManager.getTransaction().commit();

            return ownerMap;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
