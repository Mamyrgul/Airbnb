package java16.service.serviceImpl;

import java16.dao.AddressDao;
import java16.dao.daoImpl.AddressDaoImpl;
import java16.entities.Address;
import java16.entities.Agency;
import java16.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {

    AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<String> getAllAddress() {
        return addressDao.getAllAddress();
    }

    @Override
    public String update(Long id, Address address) {
        return "";
    }

    @Override
    public Address getById(Long id) {
        return null;
    }

    @Override
    public void countAgencyByCity(String city) {
        addressDao.countAgencyByCity(city);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return addressDao.groupByRegion();
    }
}
