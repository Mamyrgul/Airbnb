package java16.service;

import java16.entities.Agency;

import java.util.List;

public interface AgencyService {
    boolean updateAgency(Long id,Agency agency);
    void deleteAgency(Long id);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgency();
    void createAgencyWithAddress(String agencyName, String agPhoneNumber,
                                 String city, String region , String street);

    void createAgency(String agencyName, String agPhoneNumber);
}
