package java16.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java16.config.HibernateConfig;
import java16.dao.AddressDao;
import java16.entities.Address;
import java16.entities.Agency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressDaoImpl implements AddressDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<String> getAllAddress() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "select city , region, street, name,phonenumber from addresses " +
                         "join agencies on addresses.id = agencies.address_id ";
            Query query = entityManager.createNativeQuery(sql);
            List<Object[]> results = query.getResultList();

            List<String> addressDetails = new ArrayList<>();

            for (Object[] result : results) {
                String city = (String) result[0];
                String region = (String) result[1];
                String street = (String) result[2];
                String agencyName = (String) result[3];
                String agPhoneNumber = (String) result[4];
                addressDetails.add("Address: " + city + ", " + region + ", " + street +
                                   " | Agency: " + agencyName + agPhoneNumber);
                entityManager.close();
            }
            return addressDetails;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String update(Long id, Address address) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(address);
            entityManager.getTransaction().commit();
            return "success";
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Address getById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Address address = entityManager.find(Address.class, id);
            return address;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void countAgencyByCity(String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = """
                SELECT a.city, COUNT(ag.id)
                FROM addresses a
                JOIN agencies ag ON a.id = ag.address_id
                WHERE a.city = :city
                GROUP BY a.city
                """;
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("city", city);

            List<Object[]> result = query.getResultList();

            if (result.isEmpty()) {
                System.out.println("No agencies found in the city: " + city);
            } else {
                for (Object[] row : result) {
                    String cityName = (String) row[0];
                    Long agencyCount = ((Number) row[1]).longValue();
                    System.out.println("City: " + cityName + " | Number of Agencies: " + agencyCount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
    @Override
    public Map<String, List<Agency>> groupByRegion() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, List<Agency>> regionMap = new HashMap<>();

        try {
            String sql = """
                SELECT region, name, phonenumber
                FROM addresses
                JOIN agencies  ON addresses.id = agencies.address_id
                WHERE region IS NOT NULL
                """;

            Query query = entityManager.createNativeQuery(sql);
            List<Object[]> result = query.getResultList();

            for (Object[] row : result) {
                String region = (String) row[0];
                String name = (String) row[1];
                String phoneNumber = (String) row[2];

                regionMap.computeIfAbsent(region, k -> new ArrayList<>());

                Agency agency = new Agency(name, phoneNumber);
                regionMap.get(region).add(agency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return regionMap;
    }

}
