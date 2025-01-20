package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java16.config.HibernateConfig;
import java16.dao.AgencyDao;
import java16.entities.Address;
import java16.entities.Agency;

import java.util.List;

public class AgencyDaoImpl implements AgencyDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public void createAgency(String agencyName, String agPhoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = new Agency();
            agency.setName(agencyName);
            agency.setPhoneNumber(agPhoneNumber);
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
            System.out.println("Agency created");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createAgencyWithAddress(String agencyName, String agPhoneNumber, String city, String region, String street) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Address address = new Address();
            address.setCity(city);
            address.setRegion(region);
            address.setStreet(street);

            entityManager.persist(address);

            Agency agency = new Agency();
            agency.setName(agencyName);
            agency.setPhoneNumber(agPhoneNumber);
            agency.setAddress(address);

            entityManager.persist(agency);

            entityManager.getTransaction().commit();
            System.out.println("Agency successfully created.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating agency: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateAgency(Long id, Agency agency) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Agency existingAgency = entityManager.find(Agency.class, id);

            if (existingAgency == null) {
                return false;
            }

            existingAgency.setName(agency.getName());
            existingAgency.setPhoneNumber(agency.getPhoneNumber());
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to update agency", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    public void deleteAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);

            if (agency != null) {
                if (agency.getAddress() != null) {
                    entityManager.remove(agency.getAddress());
                }
                if (!agency.getRentInfos().isEmpty()) {
                    agency.getRentInfos().forEach(entityManager::remove);
                }
                entityManager.remove(agency);
            } else {
                System.out.println("Agency с ID " + agencyId + " не найдено.");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    @Override
    public Agency getAgencyById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Agency agency1 = entityManager.find(Agency.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return agency1;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Agency> getAllAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Agency> agencies = entityManager.createQuery("from Agency").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return agencies;
    }

}
