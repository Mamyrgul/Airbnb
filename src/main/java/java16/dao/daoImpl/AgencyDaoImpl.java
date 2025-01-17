package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java16.config.HibernateConfig;
import java16.dao.AgencyDao;
import java16.entities.Agency;

import java.util.List;

public class AgencyDaoImpl implements AgencyDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String createAgency(Agency agency) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success";
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean updateAgency(Agency agency) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(agency);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public Agency getAgencyById(Long id, String agency) {
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
