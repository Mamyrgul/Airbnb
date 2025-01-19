package java16.dao;

import java16.entities.Rent_Info;

import java.time.LocalDate;
import java.util.List;

public interface Rent_infoDao {
    List<Rent_Info> getRentInfosByDateRange(LocalDate startDate, LocalDate endDate);
    long getCurrentRentedHousesByAgency(Long agencyId);
}
