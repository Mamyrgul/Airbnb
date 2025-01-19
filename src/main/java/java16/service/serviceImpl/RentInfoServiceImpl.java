package java16.service.serviceImpl;

import java16.dao.Rent_infoDao;
import java16.dao.daoImpl.Rent_infoDaoImpl;
import java16.entities.Rent_Info;
import java16.service.RentInfoService;

import java.time.LocalDate;
import java.util.List;

public class RentInfoServiceImpl implements RentInfoService {
    Rent_infoDao rent_infoDao = new Rent_infoDaoImpl();

    @Override
    public List<Rent_Info> getRentInfosByDateRange(LocalDate startDate, LocalDate endDate) {
        return rent_infoDao.getRentInfosByDateRange(startDate, endDate);
    }

    @Override
    public long getCurrentRentedHousesByAgency(Long agencyId) {
        return rent_infoDao.getCurrentRentedHousesByAgency(agencyId);
    }
}
