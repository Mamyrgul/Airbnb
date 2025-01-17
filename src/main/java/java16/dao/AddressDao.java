package java16.dao;

import java16.entities.Address;
import java16.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
   List<String> getAllAddress();
   String update(Long id,Address address);
   Address getById(Long id);
   //Колдонуучу бир шаардын атын жазса ошол шаарда канча агентсво бар экенин эсептеп чыгарсын
   void countAgencyByCity(String city);
   Map<String, List<Agency>> groupByRegion();
}
