package java16.entities;

import jakarta.persistence.*;
import java16.enums.FamilyStatus;
import java16.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen", sequenceName = "customer_gen", allocationSize = 1)
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;
    private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;
}
