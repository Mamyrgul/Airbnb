package java16.service;

import java16.entities.Rent_Info;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoService {
    List<Rent_Info> getRentInfosByDateRange(LocalDate startDate, LocalDate endDate);
    long getCurrentRentedHousesByAgency(Long agencyId);
}
