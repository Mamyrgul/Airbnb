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
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "customer_gen", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;

    public Customer(Long id) {
        this.id = id;
    }
}
