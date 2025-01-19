package java16.entities;

import jakarta.persistence.*;
import java16.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen",sequenceName = "owner_gen",allocationSize = 1)
public class Owner extends BaseEntity{
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
   @ManyToMany(mappedBy = "owners")
    private List<Agency> agencies;
    @OneToMany(mappedBy = "owner", cascade ={ CascadeType.PERSIST,CascadeType.REMOVE})
    private List<House> houses;
    @OneToMany(mappedBy = "owner")
    private List<Rent_Info> rent_Info;

    public Owner(String firstName, String lastName, String email, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Owner(String firstName, String lastName, String email,
                 LocalDate birthDate, Gender gender,
                 List<House> houses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.houses = houses;
    }

}
