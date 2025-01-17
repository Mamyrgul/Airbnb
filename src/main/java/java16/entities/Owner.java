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
    private String email;
    private LocalDate birthDate;
    private Gender gender;
   @ManyToMany(mappedBy = "owners")
    private List<Agency> agencies;
    @OneToMany(mappedBy = "owner")
    private List<House> houses;
    @OneToMany(mappedBy = "owner")
    private List<Rent_Info> rent_Info;
}
