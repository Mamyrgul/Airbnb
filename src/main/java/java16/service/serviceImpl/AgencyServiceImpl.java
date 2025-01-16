package java16.service.serviceImpl;

import java16.dao.AgencyDao;
import java16.dao.daoImpl.AgencyDaoImpl;
import java16.entities.Agency;
import java16.service.AgencyService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();

    @Override
    public String createAgency(Agency agency) {
        return agencyDao.createAgency(agency);
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
