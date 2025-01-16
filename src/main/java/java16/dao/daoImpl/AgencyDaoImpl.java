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
        return false;
    }

    @Override
    public void deleteAgency(Agency agency) {

    }

    @Override
    public Agency getAgencyById(Long id, String agency) {
        return null;
    }

    @Override
    public List<Agency> getAllAgency() {
        return null;
    }
}
