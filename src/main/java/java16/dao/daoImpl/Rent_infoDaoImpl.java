package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java16.config.HibernateConfig;
import java16.dao.Rent_infoDao;
import java16.entities.Rent_Info;

import java.time.LocalDate;
import java.util.List;

public class Rent_infoDaoImpl implements Rent_infoDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<Rent_Info> getRentInfosByDateRange(LocalDate startDate, LocalDate endDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            List<Rent_Info> rentInfos = entityManager.createQuery(
                            "SELECT r FROM Rent_Info r WHERE r.checkOutDate BETWEEN :startDate AND :endDate", Rent_Info.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();

            entityManager.getTransaction().commit();
            return rentInfos;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error while fetching rent infos by date range", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public long getCurrentRentedHousesByAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Long count = entityManager.createQuery(
                            "SELECT COUNT(h) FROM House h JOIN h.rentInfos r " +
                            "WHERE h.address.agency.id = :agencyId AND :currentDate BETWEEN r.checkInDate AND r.checkOutDate", Long.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return count;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error while fetching rented houses by agency", e);
        } finally {
            entityManager.close();
        }
    }


}
