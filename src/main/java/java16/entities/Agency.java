package java16.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "agencies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen", sequenceName = "agency_gen", allocationSize = 1)
public class Agency extends BaseEntity {
    private String name;
    private String phoneNumber;

    @OneToOne(mappedBy = "agency", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Address address;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;

    @ManyToMany(mappedBy = "agencies")
    private List<Owner> owners;
}
