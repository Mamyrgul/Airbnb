package java16.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java16.entities.Owner;
import java16.enums.Gender;
import java16.enums.HouseType;

import java.time.LocalDate;
import java.util.Map;

public interface OwnerDao {
    String createOwner(Owner owner);

    String updateOwner(Long id, Owner owner);

    String createOwnerWithHouse(String firstName,
                                String lastName,
                                String email,
                                LocalDate birthDate,
                                Gender gender, HouseType houseType,
                                int price,
                                double rating,
                                String description,
                                int room,
                                boolean furniture);

    boolean ownerAssignAgency(Long ownerId, Long agencyId);

    void deleteOwner(Long ownerId);

    Owner getOwner(Long AgencyId);

    Map<String, Integer> getOwners();
}
