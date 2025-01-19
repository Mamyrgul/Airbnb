package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java16.config.HibernateConfig;
import java16.dao.HouseDao;
import java16.entities.*;
import java16.enums.HouseType;

import java.time.LocalDate;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String createHouse(House house) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(house);
            entityManager.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateHouse(Long id, House house) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(house);
            entityManager.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String deleteHouse(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Находим дом по ID
            House house = entityManager.find(House.class, id);
            if (house == null) {
                return "Error: House with ID " + id + " not found.";
            }

            List<Rent_Info> rentInfos = house.getRentInfos();
            if (rentInfos != null && !rentInfos.isEmpty()) {
                for (Rent_Info rentInfo : rentInfos) {
                    if (rentInfo.getCheckOut().isAfter(LocalDate.now())) {
                        return "Error: House cannot be deleted because some rents are still active.";
                    }

                    entityManager.remove(rentInfo);
                }
            }

            Address address = house.getAddress();
            if (address != null) {
                entityManager.remove(address);
            }

            entityManager.remove(house);

            entityManager.getTransaction().commit();
            return "Success: House deleted successfully.";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error occurred while deleting house: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public House getHouse(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            entityManager.getTransaction().commit();
            return house;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<House> getHouses() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<House> houses = entityManager.createQuery("from House").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return houses;
    }

    @Override
    public boolean createHouseWithOwner(Long ownerId, HouseType houseType, int price, double rating, String description, int room, boolean furniture) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            if (owner == null) {
                throw new RuntimeException("Error: Owner with ID " + ownerId + " not found.");
            }
            House house = new House();
            house.setOwner(owner);
            house.setHouseType(houseType);
            house.setPrice(price);
            house.setRating(rating);
            house.setDescription(description);
            house.setRoom(room);
            house.setFurniture(furniture);
            entityManager.persist(house);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<House> getHousesByRegion(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            List<House> houses = entityManager.createQuery(
                            "SELECT h FROM House h WHERE h.address.region = :region", House.class)
                    .setParameter("region", region)
                    .getResultList();

            entityManager.getTransaction().commit();

            if (houses.isEmpty()) {
                throw new RuntimeException("Error: No houses found in region " + region);
            }

            return houses;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error occurred while retrieving houses by region: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<House> getHousesByAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Agency agency = entityManager.find(Agency.class, agencyId);
            if (agency == null) {
                throw new RuntimeException("Agency not found with ID: " + agencyId);
            }

            List<House> houses = entityManager.createQuery(
                            "SELECT h FROM House h WHERE h.address.agency.id = :agencyId", House.class)
                    .setParameter("agencyId", agencyId)
                    .getResultList();
            entityManager.getTransaction().commit();

            if (houses.isEmpty()) {
                throw new RuntimeException("No houses found for agency with ID: " + agencyId);
            }

            return houses;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error occurred while retrieving houses by agency: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
    @Override
    public List<House> getHousesByOwner(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Owner owner = entityManager.find(Owner.class, ownerId);
            if (owner == null) {
                throw new RuntimeException("Owner not found with ID: " + ownerId);
            }

            List<House> houses = entityManager.createQuery(
                            "SELECT h FROM House h WHERE h.owner.id = :ownerId", House.class)
                    .setParameter("ownerId", ownerId)
                    .getResultList();

            entityManager.getTransaction().commit();
            return houses;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error while fetching houses by owner", e);
        } finally {
            entityManager.close();
        }
    }
    @Override
    public List<House> getHousesByRentInfo(LocalDate checkInDate, LocalDate checkOutDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            List<House> houses = entityManager.createQuery(
                            "SELECT DISTINCT h FROM House h JOIN h.rentInfos r " +
                            "WHERE r.checkInDate >= :checkInDate AND r.checkOutDate <= :checkOutDate", House.class)
                    .setParameter("checkInDate", checkInDate)
                    .setParameter("checkOutDate", checkOutDate)
                    .getResultList();

            entityManager.getTransaction().commit();
            return houses;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error while fetching houses by rent info", e);
        } finally {
            entityManager.close();
        }
    }

}
