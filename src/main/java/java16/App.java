package java16;

import java16.config.HibernateConfig;
import java16.dao.AgencyDao;
import java16.dao.CustomerDao;
import java16.dao.HouseDao;
import java16.dao.OwnerDao;
import java16.dao.daoImpl.AgencyDaoImpl;
import java16.dao.daoImpl.CustomerDaoImpl;
import java16.dao.daoImpl.HouseDaoImpl;
import java16.dao.daoImpl.OwnerDaoImpl;
import java16.entities.*;
import java16.enums.FamilyStatus;
import java16.enums.Gender;
import java16.enums.HouseType;
import java16.service.*;
import java16.service.serviceImpl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerLong = new Scanner(System.in);

        AgencyService agencyService = new AgencyServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        RentInfoService rentInfoService = new RentInfoServiceImpl();


        while (true) {
            System.out.println("\n=== Главное Меню ===");
            System.out.println("1.Создать агенство вместе адрес");
            System.out.println("2. Создать агентство");
            System.out.println("3. Обновить агентство");
            System.out.println("4. Удалить агентство вместе адрес и информации аренде");
            System.out.println("5. Получить агентство по ID");

            System.out.println("6. Группировать адрес по регионам");
            System.out.println("7. Обновить адрес");
            System.out.println("8. Получить все адресы вместе агенство");
            System.out.println("9. Получить количество агенство по название города");
            System.out.println("10. Получить адрес через айди");


            System.out.println("11. Создать клиента");
            System.out.println("12. Создать клиент вместе арендованный дом");
            System.out.println("13. Получить клиента по айди");
            System.out.println("14. Обновить клиента");
            System.out.println("15. Ижарага алып жатканда customer id, house id, agency id жана check in check out жазсын");
            System.out.println("16. Удалить клиента");


            System.out.println("17. Создать владельца");
            System.out.println("18. Создать владельца вместе дом");
            System.out.println("19. Обновить владельца");
            System.out.println("20. Соединить владельца агенству");
            System.out.println("21. Удалить владельца");
            System.out.println("22. Получить владельца по айди агенство");
            System.out.println("23. Получить все имя и возраст владельца");


            System.out.println("24. Создать дом");
            System.out.println("25. Создать дом зависима владельца");
            System.out.println("26. Удалить дом");
            System.out.println("27. Обновить дом");
            System.out.println("28. Получить дом по айди");
            System.out.println("29. Получть дома по агентству");
            System.out.println("30. Получить дома по региону");
            System.out.println("31. Получить дома по владельцу");
            System.out.println("32. Получить все дома между двумя датами");

            System.out.println("33. Получить все информации между двумя датами");
            System.out.println("34. Посчитать арендованные домы через agency id");
            System.out.println("35. Завершить");
            System.out.print("Выберите действие: ");
            int choice = scannerInt.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите название агентства: ");
                    String agencyName = scannerStr.nextLine();
                    System.out.print("Введите номер телефона агентства: ");
                    String agPhoneNumber = scannerStr.nextLine();
                    System.out.print("Введите город: ");
                    String city = scannerStr.nextLine();
                    System.out.print("Введите регион: ");
                    String region = scannerStr.nextLine();
                    System.out.print("Введите улицу: ");
                    String street = scannerStr.nextLine();
                    agencyService.createAgencyWithAddress(agencyName, agPhoneNumber, city, region, street);
                    break;
                case 2:
                    System.out.print("Введите название агентства: ");
                    agencyName = scannerStr.nextLine();
                    System.out.print("Введите номер телефона агентства: ");
                    agPhoneNumber = scannerStr.nextLine();
                    agencyService.createAgency(agencyName, agPhoneNumber);
                    break;
                case 3:
                    System.out.print("Введите ID агентства для обновления: ");
                    Long agencyId = scannerLong.nextLong();
                    scannerLong.nextLine();

                    System.out.print("Введите новое название агентства: ");
                    agencyName = scannerStr.nextLine();

                    System.out.print("Введите новый номер телефона агентства: ");
                    agPhoneNumber = scannerStr.nextLine();

                    Agency updatedAgency = new Agency();
                    updatedAgency.setName(agencyName);
                    updatedAgency.setPhoneNumber(agPhoneNumber);

                    boolean result = agencyService.updateAgency(agencyId, updatedAgency);
                    System.out.println(result);

                    if (result) {
                        System.out.println("Агентство успешно обновлено.");
                    } else {
                        System.out.println("Агентство с данным ID не найдено.");
                    }
                    break;

                case 4:
                    System.out.print("Введите ID агентства для удаления: ");
                    agencyId = scannerLong.nextLong();
                    agencyService.deleteAgency(agencyId);
                    break;
                case 5:
                    System.out.print("Введите ID агентства: ");
                    agencyId = scannerLong.nextLong();
                    System.out.println(agencyService.getAgencyById(agencyId));
                    break;

                case 6:
                    System.out.println(addressService.groupByRegion());
                    break;

                case 7:
                    System.out.print("Введите ID адреса для обновления: ");
                    Long addressId = scannerLong.nextLong();
                    scannerLong.nextLine();

                    System.out.print("Введите новый город: ");
                    city = scannerStr.nextLine();

                    System.out.print("Введите новый регион: ");
                    region = scannerStr.nextLine();

                    System.out.print("Введите новую улицу: ");
                    street = scannerStr.nextLine();

                    Address updatedAddress = new Address();
                    updatedAddress.setCity(city);
                    updatedAddress.setRegion(region);
                    updatedAddress.setStreet(street);

                    String result1 = addressService.update(addressId, updatedAddress);
                    System.out.println(result1);

                    if ("success".equals(result1)) {
                        System.out.println("Адрес успешно обновлен.");
                    } else {
                        System.out.println("Ошибка обновления адреса.");
                    }
                    break;

                case 8:
                    System.out.println(addressService.getAllAddress());
                    break;

                case 9:
                    System.out.print("Введите название города: ");
                    city = scannerStr.nextLine();
                    addressService.countAgencyByCity(city);
                    break;

                case 10:

                    System.out.print("Введите ID адреса: ");
                    addressId = scannerLong.nextLong();
                    System.out.println(addressService.getById(addressId));
                    break;
                case 11:
                    System.out.println("Введите имя клиента: ");
                    String firstName = scannerStr.nextLine();

                    System.out.println("Введите фамилию клиента: ");
                    String lastName = scannerStr.nextLine();

                    System.out.println("Введите email клиента: ");
                    String email = scannerStr.nextLine();

                    System.out.println("Введите дату рождения клиента (yyyy-mm-dd): ");
                    String birthDateString = scannerStr.nextLine();
                    LocalDate birthDate = LocalDate.parse(birthDateString);

                    System.out.println("Введите пол клиента (MALE/FEMALE/OTHER): ");
                    String genderString = scannerStr.nextLine();
                    Gender gender = Gender.valueOf(genderString.toUpperCase());

                    System.out.println("Введите национальность клиента: ");
                    String nationality = scannerStr.nextLine();

                    System.out.println("Введите семейное положение клиента (SINGLE/MARRIED/DIVORCED/WIDOWED): ");
                    String familyStatusString = scannerStr.nextLine();
                    FamilyStatus familyStatus = FamilyStatus.valueOf(familyStatusString.toUpperCase());

                    Customer customer = new Customer();
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);
                    customer.setBirthDate(birthDate);
                    customer.setGender(gender);
                    customer.setNationality(nationality);
                    customer.setFamilyStatus(familyStatus);

                    customerService.createCustomer(customer);
                    System.out.println("Клиент успешно создан.");
                    break;

                case 12:
                    System.out.println("Введите имя клиента: ");
                    firstName = scannerStr.nextLine();

                    System.out.println("Введите фамилию клиента: ");
                    lastName = scannerStr.nextLine();

                    System.out.println("Введите email клиента: ");
                    email = scannerStr.nextLine();

                    System.out.println("Введите дату рождения клиента (yyyy-mm-dd): ");
                    birthDateString = scannerStr.nextLine();
                    birthDate = LocalDate.parse(birthDateString);

                    System.out.println("Введите пол клиента (MALE/FEMALE): ");
                    genderString = scannerStr.nextLine();
                    gender = Gender.valueOf(genderString.toUpperCase());

                    System.out.println("Введите национальность клиента: ");
                    nationality = scannerStr.nextLine();

                    System.out.println("Введите семейное положение клиента (SINGLE/MARRIED/DIVORCED/WIDOWED): ");
                    familyStatusString = scannerStr.nextLine();
                    familyStatus = FamilyStatus.valueOf(familyStatusString.toUpperCase());

                    System.out.println("Введите ID дома: ");
                    Long houseId = scannerLong.nextLong();

                    System.out.println("Введите дату начала аренды (yyyy-mm-dd): ");
                    String rentStartDateString = scannerStr.nextLine();
                    LocalDate rentStartDate = LocalDate.parse(rentStartDateString);

                    System.out.println("Введите дату окончания аренды (yyyy-mm-dd): ");
                    String rentEndDateString = scannerStr.nextLine();
                    LocalDate rentEndDate = LocalDate.parse(rentEndDateString);

                    customerService.createCustomerWithRent(firstName, lastName, email, birthDate, gender, nationality, familyStatus, houseId, rentStartDate, rentEndDate);
                    System.out.println("Клиент с арендованным домом успешно создан.");
                    break;

                case 13:
                    System.out.print("Введите ID клиента: ");
                    Long customerId = scannerLong.nextLong();
                    Customer retrievedCustomer = customerService.getCustomer(customerId);

                    if (retrievedCustomer != null) {
                        System.out.println("Клиент найден: ");
                        System.out.println("Имя: " + retrievedCustomer.getFirstName());
                        System.out.println("Фамилия: " + retrievedCustomer.getLastName());
                        System.out.println("Email: " + retrievedCustomer.getEmail());
                        System.out.println("Дата рождения: " + retrievedCustomer.getBirthDate());
                        System.out.println("Пол: " + retrievedCustomer.getGender());
                        System.out.println("Национальность: " + retrievedCustomer.getNationality());
                        System.out.println("Семейное положение: " + retrievedCustomer.getFamilyStatus());
                    } else {
                        System.out.println("Клиент не найден.");
                    }
                    break;

                case 14:

                    System.out.print("Введите ID клиента для обновления: ");
                    customerId = scannerLong.nextLong();
                    scannerLong.nextLine();

                    System.out.print("Введите новое имя клиента: ");
                    firstName = scannerStr.nextLine();

                    System.out.print("Введите новую фамилию клиента: ");
                    lastName = scannerStr.nextLine();

                    System.out.print("Введите новый email клиента: ");
                    email = scannerStr.nextLine();

                    System.out.print("Введите новую дату рождения клиента (yyyy-mm-dd): ");
                    birthDateString = scannerStr.nextLine();
                    birthDate = LocalDate.parse(birthDateString);

                    System.out.print("Введите новый пол клиента (MALE/FEMALE/OTHER): ");
                    genderString = scannerStr.nextLine();
                    gender = Gender.valueOf(genderString.toUpperCase());

                    System.out.print("Введите новую национальность клиента: ");
                    nationality = scannerStr.nextLine();

                    System.out.print("Введите новое семейное положение клиента (SINGLE/MARRIED/DIVORCED/WIDOWED): ");
                    familyStatusString = scannerStr.nextLine();
                    familyStatus = FamilyStatus.valueOf(familyStatusString.toUpperCase());

                    Customer updatedCustomer = new Customer();
                    updatedCustomer.setFirstName(firstName);
                    updatedCustomer.setLastName(lastName);
                    updatedCustomer.setEmail(email);
                    updatedCustomer.setBirthDate(birthDate);
                    updatedCustomer.setGender(gender);
                    updatedCustomer.setNationality(nationality);
                    updatedCustomer.setFamilyStatus(familyStatus);

                    customerService.updateCustomer(customerId, updatedCustomer);
                    System.out.println("Клиент успешно обновлен.");
                    break;

                case 15:
                    System.out.print("Введите ID клиента: ");
                    customerId = scannerLong.nextLong();

                    System.out.print("Введите ID дома: ");
                    houseId = scannerLong.nextLong();

                    System.out.print("Введите ID агентства: ");
                    Long agencyIdd = scannerLong.nextLong();

                    System.out.print("Введите дату заезда (yyyy-mm-dd): ");
                    String checkInString = scannerStr.nextLine();
                    LocalDate checkIn = LocalDate.parse(checkInString);

                    System.out.print("Введите дату выезда (yyyy-mm-dd): ");
                    String checkOutString = scannerStr.nextLine();
                    LocalDate checkOut = LocalDate.parse(checkOutString);

                    customerService.arendHouse(customerId, houseId, agencyIdd, checkIn, checkOut);
                    System.out.println("Информация о аренде успешно добавлена.");
                    break;

                case 16:

                    System.out.print("Введите ID клиента для удаления: ");
                    customerId = scannerLong.nextLong();

                    boolean deleted = customerService.deleteCustomer(customerId);
                    if (deleted) {
                        System.out.println("Клиент успешно удален.");
                    } else {
                        System.out.println("Не удалось удалить клиента.");
                    }
                    break;
                case 17:
                    System.out.println("Введите имя владельца: ");
                    String firstNamee = scannerStr.nextLine();

                    System.out.println("Введите фамилию владельца: ");
                    String lastNamee = scannerStr.nextLine();

                    System.out.println("Введите email владельца: ");
                    String emaill = scannerStr.nextLine();

                    System.out.println("Введите дату рождения владельца (yyyy-mm-dd): ");
                    String birthDateStringg = scannerStr.nextLine();
                    LocalDate birthDatee = LocalDate.parse(birthDateStringg);

                    System.out.println("Введите пол владельца (MALE/FEMALE/OTHER): ");
                    String genderStringg = scannerStr.nextLine();
                    Gender genderr = Gender.valueOf(genderStringg.toUpperCase());

                    Owner owner = new Owner();
                    owner.setFirstName(firstNamee);
                    owner.setLastName(lastNamee);
                    owner.setEmail(emaill);
                    owner.setBirthDate(birthDatee);
                    owner.setGender(genderr);

                    ownerService.createOwner(owner);
                    System.out.println("Владелец успешно создан.");
                    break;

                case 18:
                    System.out.println("Введите имя владельца: ");
                    firstName = scannerStr.nextLine();

                    System.out.println("Введите фамилию владельца: ");
                    lastName = scannerStr.nextLine();

                    System.out.println("Введите email владельца: ");
                    email = scannerStr.nextLine();

                    System.out.println("Введите дату рождения владельца (yyyy-mm-dd): ");
                    birthDateString = scannerStr.nextLine();
                    birthDate = LocalDate.parse(birthDateString);

                    System.out.println("Введите пол владельца (MALE/FEMALE): ");
                    genderString = scannerStr.nextLine();
                    gender = Gender.valueOf(genderString.toUpperCase());

                    System.out.println("Введите тип дома (APARTMENT, HOUSE, VILLA, STUDIO,COTTAGE): ");
                    String houseTypeString = scannerStr.nextLine();
                    HouseType houseType = HouseType.valueOf(houseTypeString.toUpperCase());

                    System.out.println("Введите цену дома: ");
                    int price = scannerInt.nextInt();

                    System.out.println("Введите рейтинг дома: ");
                    double rating = scannerInt.nextDouble();

                    scannerStr.nextLine();
                    System.out.println("Введите описание дома: ");
                    String description = scannerStr.nextLine();

                    System.out.println("Введите количество комнат: ");
                    int room = scannerInt.nextInt();

                    System.out.println("Есть ли мебель? (true/false): ");
                    boolean furniture = scannerInt.nextBoolean();

                    ownerService.createOwnerWithHouse(firstName, lastName, email, birthDate, gender, houseType, price, rating, description, room, furniture);
                    System.out.println("Владелец с домом успешно создан.");
                    break;

                case 19:
                    System.out.print("Введите ID владельца для обновления: ");
                    Long ownerId = scannerLong.nextLong();
                    scannerLong.nextLine();

                    System.out.print("Введите новое имя владельца: ");
                    firstName = scannerStr.nextLine();

                    System.out.print("Введите новую фамилию владельца: ");
                    lastName = scannerStr.nextLine();

                    System.out.print("Введите новый email владельца: ");
                    email = scannerStr.nextLine();

                    System.out.print("Введите новую дату рождения владельца (yyyy-mm-dd): ");
                    birthDateString = scannerStr.nextLine();
                    birthDate = LocalDate.parse(birthDateString);

                    System.out.print("Введите новый пол владельца (MALE/FEMALE/OTHER): ");
                    genderString = scannerStr.nextLine();
                    gender = Gender.valueOf(genderString.toUpperCase());

                    Owner updatedOwner = new Owner();
                    updatedOwner.setFirstName(firstName);
                    updatedOwner.setLastName(lastName);
                    updatedOwner.setEmail(email);
                    updatedOwner.setBirthDate(birthDate);
                    updatedOwner.setGender(gender);

                    ownerService.updateOwner(ownerId, updatedOwner);
                    System.out.println("Владелец успешно обновлен.");
                    break;

                case 20:
                    System.out.print("Введите ID владельца: ");
                    ownerId = scannerLong.nextLong();

                    System.out.print("Введите ID агентства: ");
                    Long agencyIddd = scannerLong.nextLong();

                    boolean success = ownerService.ownerAssignAgency(ownerId, agencyIddd);
                    if (success) {
                        System.out.println("Владелец успешно подключен к агентству.");
                    } else {
                        System.out.println("Не удалось подключить владельца к агентству.");
                    }
                    break;

                case 21:
                    System.out.print("Введите ID владельца для удаления: ");
                    ownerId = scannerLong.nextLong();

                    ownerService.deleteOwner(ownerId);
                    System.out.println("Владелец успешно удален.");
                    break;

                case 22:
                    System.out.print("Введите ID агентства: ");
                    Long agencyIdForOwner = scannerLong.nextLong();

                    Owner ownerByAgency = ownerService.getOwner(agencyIdForOwner);
                    if (ownerByAgency != null) {
                        System.out.println("Владелец найден: ");
                        System.out.println("Имя: " + ownerByAgency.getFirstName());
                        System.out.println("Фамилия: " + ownerByAgency.getLastName());
                        System.out.println("Email: " + ownerByAgency.getEmail());
                        System.out.println("Дата рождения: " + ownerByAgency.getBirthDate());
                        System.out.println("Пол: " + ownerByAgency.getGender());
                    } else {
                        System.out.println("Владелец с таким ID не найден.");
                    }
                    break;

                case 23:
                    Map<String, Integer> owners = ownerService.getOwners();
                    for (Map.Entry<String, Integer> entry : owners.entrySet()) {
                        System.out.println("Имя владельца: " + entry.getKey() + ", Возраст: " + entry.getValue());
                    }
                    break;
                case 24:
                    try {
                        System.out.println("Введите ID существующего адреса: ");
                        Long addressIdd = scannerLong.nextLong();

                        scannerStr.nextLine();

                        System.out.println("Введите тип дома (HOUSE/CONDO/APARTMENT): ");
                        String houseTypeStringg = scannerStr.nextLine();
                        HouseType houseTypee = HouseType.valueOf(houseTypeStringg.toUpperCase());

                        System.out.println("Введите цену дома: ");
                        int pricee = scannerInt.nextInt();

                        System.out.println("Введите рейтинг дома: ");
                        double ratingg = scannerInt.nextDouble();

                        scannerStr.nextLine();

                        System.out.println("Введите описание дома: ");
                        String descriptionn = scannerStr.nextLine();

                        System.out.println("Введите количество комнат: ");
                        int roomm = scannerInt.nextInt();

                        System.out.println("Есть ли мебель? (true/false): ");
                        boolean furnituree = scannerInt.nextBoolean();

                        String resultt = houseService.createHouse(addressIdd, houseTypee, pricee, ratingg, descriptionn, roomm, furnituree);
                        System.out.println(resultt);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: Неверный ввод данных. Пожалуйста, проверьте тип дома или формат данных.");
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;


                case 25:
                    System.out.print("Введите ID владельца: ");
                    Long ownerid = scannerLong.nextLong();

                    System.out.println("Введите тип дома (APARTMENT, HOUSE, VILLA, STUDIO,COTTAGE): ");
                    houseTypeString = scannerStr.nextLine();
                    houseType = HouseType.valueOf(houseTypeString.toUpperCase());

                    System.out.println("Введите цену дома: ");
                    price = scannerInt.nextInt();

                    System.out.println("Введите рейтинг дома: ");
                    rating = scannerInt.nextDouble();

                    scannerStr.nextLine();
                    System.out.println("Введите описание дома: ");
                    description = scannerStr.nextLine();

                    System.out.println("Введите количество комнат: ");
                    room = scannerInt.nextInt();

                    System.out.println("Есть ли мебель? (true/false): ");
                    furniture = scannerInt.nextBoolean();

                    houseService.createHouseWithOwner(ownerid, houseType, price, rating, description, room, furniture);
                    System.out.println("Дом с владельцем успешно создан.");
                    break;

                case 26:
                    System.out.print("Введите ID дома для удаления: ");
                    Long housed = scannerLong.nextLong();

                    houseService.deleteHouse(housed);
                    System.out.println("Дом успешно удален.");
                    break;

                case 27:
                    System.out.print("Введите ID дома для обновления: ");
                    houseId = scannerLong.nextLong();
                    scannerLong.nextLine();
                    System.out.print("Введите новый тип дома (HOUSE/CONDO/APPARTMENT): ");
                    houseTypeString = scannerStr.nextLine();
                    houseType = HouseType.valueOf(houseTypeString.toUpperCase());

                    System.out.print("Введите новую цену дома: ");
                    price = scannerInt.nextInt();

                    System.out.print("Введите новый рейтинг дома: ");
                    rating = scannerInt.nextDouble();

                    scannerStr.nextLine();
                    System.out.print("Введите новое описание дома: ");
                    description = scannerStr.nextLine();

                    System.out.print("Введите новое количество комнат: ");
                    room = scannerInt.nextInt();

                    System.out.print("Есть ли мебель? (true/false): ");
                    furniture = scannerInt.nextBoolean();

                    House updatedHouse = new House();
                    updatedHouse.setHouseType(houseType);
                    updatedHouse.setPrice(price);
                    updatedHouse.setRating(rating);
                    updatedHouse.setDescription(description);
                    updatedHouse.setRoom(room);
                    updatedHouse.setFurniture(furniture);

                    houseService.updateHouse(houseId, updatedHouse);
                    System.out.println("Дом успешно обновлен.");
                    break;

                case 28:
                    System.out.print("Введите ID дома: ");
                    houseId = scannerLong.nextLong();

                    House house = houseService.getHouse(houseId);
                    if (house != null) {
                        System.out.println("Дом найден: ");
                        System.out.println("Тип дома: " + house.getHouseType());
                        System.out.println("Цена: " + house.getPrice());
                        System.out.println("Рейтинг: " + house.getRating());
                        System.out.println("Описание: " + house.getDescription());
                        System.out.println("Комнат: " + house.getRoom());
                        System.out.println("Мебель: " + house.isFurniture());
                    } else {
                        System.out.println("Дом с таким ID не найден.");
                    }
                    break;

                case 29:
                    System.out.print("Введите ID агентства: ");
                    Long agencyI = scannerLong.nextLong();

                    List<House> housesByAgency = houseService.getHousesByAgency(agencyI);
                    if (housesByAgency.isEmpty()) {
                        System.out.println("Нет домов для этого агентства.");
                    } else {
                        System.out.println("Дома для этого агентства:");
                        for (House h : housesByAgency) {
                            System.out.println("ID дома: " + h.getId() + ", Тип: " + h.getHouseType() + ", Цена: " + h.getPrice());
                        }
                    }
                    break;

                case 30:
                    System.out.print("Введите регион: ");
                    String regio = scannerStr.nextLine();

                    List<House> housesByRegion = houseService.getHousesByRegion(regio);
                    if (housesByRegion.isEmpty()) {
                        System.out.println("Нет домов в этом регионе.");
                    } else {
                        System.out.println("Дома в этом регионе:");
                        for (House h : housesByRegion) {
                            System.out.println("ID дома: " + h.getId() + ", Тип: " + h.getHouseType() + ", Цена: " + h.getPrice());
                        }
                    }
                    break;

                case 31:
                    System.out.print("Введите ID владельца: ");
                    Long ownerIdForHouse = scannerLong.nextLong();

                    List<House> housesByOwner = houseService.getHousesByOwner(ownerIdForHouse);
                    if (housesByOwner.isEmpty()) {
                        System.out.println("Нет домов для этого владельца.");
                    } else {
                        System.out.println("Дома для этого владельца:");
                        for (House h : housesByOwner) {
                            System.out.println("ID дома: " + h.getId() + ", Тип: " + h.getHouseType() + ", Цена: " + h.getPrice());
                        }
                    }
                    break;

                case 32:
                    System.out.print("Введите дату начала аренды (yyyy-mm-dd): ");
                    String checkInDateString = scannerStr.nextLine();
                    LocalDate checkInDate = LocalDate.parse(checkInDateString);

                    System.out.print("Введите дату окончания аренды (yyyy-mm-dd): ");
                    String checkOutDateString = scannerStr.nextLine();
                    LocalDate checkOutDate = LocalDate.parse(checkOutDateString);

                    List<House> housesByRentInfo = houseService.getHousesByRentInfo(checkInDate, checkOutDate);
                    if (housesByRentInfo.isEmpty()) {
                        System.out.println("Нет домов в этот период.");
                    } else {
                        System.out.println("Дома в этот период:");
                        for (House h : housesByRentInfo) {
                            System.out.println("ID дома: " + h.getId() + ", Тип: " + h.getHouseType() + ", Цена: " + h.getPrice());
                        }
                    }
                    break;
                case 33:
                    System.out.print("Введите дату начала (yyyy-mm-dd): ");
                    String startDateString = scannerStr.nextLine();
                    LocalDate startDate = LocalDate.parse(startDateString);

                    System.out.print("Введите дату окончания (yyyy-mm-dd): ");
                    String endDateString = scannerStr.nextLine();
                    LocalDate endDate = LocalDate.parse(endDateString);

                    List<Rent_Info> rentInfos = rentInfoService.getRentInfosByDateRange(startDate, endDate);
                    if (rentInfos.isEmpty()) {
                        System.out.println("Нет информации по аренде в этот период.");
                    } else {
                        System.out.println("Информация по аренде между этими датами:");
                        for (Rent_Info rentInfo : rentInfos) {
                            System.out.println("ID аренды: " + rentInfo.getId() + ", ID клиента: " + rentInfo.getCustomer() +
                                               ", ID дома: " + rentInfo.getHouse() + ", Дата заезда: " + rentInfo.getCheckIn() + ", Дата выезда: " + rentInfo.getCheckOut());
                        }
                    }
                    break;

                case 34:
                    System.out.print("Введите ID агентства: ");
                    Long agency = scannerLong.nextLong();

                    long rentedHousesCount = rentInfoService.getCurrentRentedHousesByAgency(agency);
                    System.out.println("Количество арендованных домов через агентство с ID " + agency + ": " + rentedHousesCount);
                    break;
            }
        }
    }
}
