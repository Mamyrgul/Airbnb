package java16.service.serviceImpl;

import java16.dao.HouseDao;
import java16.dao.daoImpl.HouseDaoImpl;
import java16.entities.House;
import java16.enums.HouseType;
import java16.service.HouseService;

import java.time.LocalDate;
import java.util.List;

public class HouseServiceImpl implements HouseService {
    HouseDao houseDao = new HouseDaoImpl();

    @Override
    public String createHouse(House house) {
        return houseDao.createHouse(house);
    }

    @Override
    public String updateHouse(Long id, House house) {
        return houseDao.updateHouse(id, house);
    }

    @Override
    public String deleteHouse(Long id) {
        return houseDao.deleteHouse(id);
    }

    @Override
    public House getHouse(Long id) {
        return houseDao.getHouse(id);
    }

    @Override
    public List<House> getHouses() {
        return houseDao.getHouses();
    }

    @Override
    public boolean createHouseWithOwner(Long ownerId, HouseType houseType, int price, double rating, String description, int room, boolean furniture) {
        return houseDao.createHouseWithOwner(ownerId, houseType, price, rating, description, room, furniture);
    }

    @Override
    public List<House> getHousesByRegion(String region) {
        return houseDao.getHousesByRegion(region);
    }

    @Override
    public List<House> getHousesByAgency(Long agencyId) {
        return houseDao.getHousesByAgency(agencyId);
    }

    @Override
    public List<House> getHousesByOwner(Long ownerId) {
        return houseDao.getHousesByOwner(ownerId);
    }

    @Override
    public List<House> getHousesByRentInfo(LocalDate checkInDate, LocalDate checkOutDate) {
        return houseDao.getHousesByRentInfo(checkInDate, checkOutDate);
    }
}
