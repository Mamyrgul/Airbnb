package java16.dao;

import java16.entities.Agency;

import java.util.List;

public interface AgencyDao {
    String createAgency(Agency agency);
    boolean updateAgency(Agency agency);
    void deleteAgency(Long id);
    Agency getAgencyById(Long id,String agency);
    List<Agency> getAllAgency();

}
