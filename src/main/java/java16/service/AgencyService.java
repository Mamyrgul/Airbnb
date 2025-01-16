package java16.service;

import java16.entities.Agency;

import java.util.List;

public interface AgencyService {
    String createAgency(Agency agency);
    boolean updateAgency(Agency agency);
    void deleteAgency(Agency agency);
    Agency getAgencyById(Long id,String agency);
    List<Agency> getAllAgency();

}
