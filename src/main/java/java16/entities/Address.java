package java16.entities;

import jakarta.persistence.*;
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
    @Column(unique = true)
    private String street;

    @OneToOne(mappedBy = "address")
    private Agency agency;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private House house;

    public Address(String city, String region, String street, Agency agency) {
        this.city = city;
        this.region = region;
        this.street = street;
        this.agency = agency;
    }

}
