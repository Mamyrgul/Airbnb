package java16.service.serviceImpl;

import java16.dao.OwnerDao;
import java16.dao.daoImpl.OwnerDaoImpl;
import java16.entities.Owner;
import java16.enums.Gender;
import java16.enums.HouseType;
import java16.service.OwnerService;

import java.time.LocalDate;
import java.util.Map;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public String createOwner(Owner owner) {
        return ownerDao.createOwner(owner);
    }

    @Override
    public String updateOwner(Long id, Owner owner) {
        return ownerDao.updateOwner(id, owner);
    }

    @Override
    public String createOwnerWithHouse(String firstName, String lastName, String email, LocalDate birthDate, Gender gender, HouseType houseType, int price, double rating, String description, int room, boolean furniture) {
        return ownerDao.createOwnerWithHouse(firstName,lastName, email, birthDate, gender,houseType, price,rating,description,room,furniture);
    }

    @Override
    public boolean ownerAssignAgency(Long ownerId, Long agencyId) {
        return ownerDao.ownerAssignAgency(ownerId,agencyId);
    }

    @Override
    public void deleteOwner(Long ownerId) {
      ownerDao.deleteOwner(ownerId);
    }

    @Override
    public Owner getOwner(Long AgencyId) {
        return ownerDao.getOwner(AgencyId);
    }

    @Override
    public Map<String, Integer> getOwners() {
        return ownerDao.getOwners();
    }
}
