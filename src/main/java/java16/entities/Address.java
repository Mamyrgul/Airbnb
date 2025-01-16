package java16.entities;

import jakarta.persistence.*;
import java16.entities.Agency;
import java16.entities.BaseEntity;
import java16.entities.House;
import lombok.*;
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen", sequenceName = "address_gen", allocationSize = 1)
public class Address extends BaseEntity {
    private String city;
    private String region;
    private String street;

    @OneToOne
    private Agency agency;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private House house;
}
