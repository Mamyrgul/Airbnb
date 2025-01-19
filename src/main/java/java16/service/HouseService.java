package java16.service;

import java16.entities.House;
import java16.enums.HouseType;

import java.time.LocalDate;
import java.util.List;

public interface HouseService {
    String createHouse(House house);

    String updateHouse(Long id, House house);

    String deleteHouse(Long id);

    House getHouse(Long id);

    List<House> getHouses();

    boolean createHouseWithOwner(Long ownerId, HouseType houseType,
                                 int price, double rating, String description,
                                 int room,
                                 boolean furniture);
    List<House> getHousesByRegion(String region);
    List<House> getHousesByAgency(Long agencyId);
    List<House> getHousesByOwner(Long ownerId);
    List<House> getHousesByRentInfo(LocalDate checkInDate, LocalDate checkOutDate);
}
