package java16;

import java16.config.HibernateConfig;
import java16.entities.Address;
import java16.entities.Agency;
import java16.service.AgencyService;
import java16.service.serviceImpl.AgencyServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        HibernateConfig.getEntityManagerFactory();
       /* Agency agency = new Agency();
        agency.setName("Dream Homes");
        agency.setPhoneNumber("+123456789");

// Создаём адрес
        Address address = new Address();
        address.setCity("New York");
        address.setRegion("NY");
        address.setStreet("123 Main Street");

// Связываем адрес с агентством
        agency.setAddress(address);
        AgencyService agencyService = new AgencyServiceImpl();
// Сохраняем агентство, адрес сохраняется автоматически
       agencyService.createAgency(agency);

        */


    }
}
