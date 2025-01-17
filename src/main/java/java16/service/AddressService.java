package java16.service;

import java16.entities.Address;
import java16.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressService {
    List<String> getAllAddress();
    String update(Long id, Address address);
    Address getById(Long id);
    void countAgencyByCity(String city);
    Map<String, List<Agency>> groupByRegion();

}
