package java16.service.serviceImpl;

import jakarta.persistence.EntityManager;
import java16.dao.AgencyDao;
import java16.dao.daoImpl.AgencyDaoImpl;
import java16.entities.Agency;
import java16.service.AgencyService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();


    @Override
    public boolean updateAgency(Long id,Agency agency) {
        return agencyDao.updateAgency(id,agency);
    }

    @Override
    public void deleteAgency(Long id) {
      agencyDao.deleteAgency(id);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyDao.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyDao.getAllAgency();
    }

    @Override
    public void createAgencyWithAddress(String agencyName, String agPhoneNumber, String city, String region, String street) {
        agencyDao.createAgencyWithAddress(agencyName,agPhoneNumber,city,region,street);
    }

    @Override
    public void createAgency(String agencyName, String agPhoneNumber) {
        agencyDao.createAgency(agencyName,agPhoneNumber);
    }
}
