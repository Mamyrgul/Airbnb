package java16.dao;

import java16.entities.Agency;

import java.util.List;

public interface AgencyDao {
    void createAgency(String agencyName, String agPhoneNumber);
    void createAgencyWithAddress(String agencyName, String agPhoneNumber,
                                 String city, String region , String street);
    boolean updateAgency(Long id,Agency agency);
    void deleteAgency(Long id);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgency();

}
