package java16.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;

    @ManyToMany
    private List<Owner> owners;

    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Agency(String name, String phoneNumber, Address address, List<Rent_Info> rentInfos) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rentInfos = rentInfos;
    }
}
