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
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "agency_gen", allocationSize = 1)
    private Long id;
    private String name;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Rent_Info> rentInfos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Owner> owners;

    public Agency(String name, String phoneNumber, Address address) {
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

    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Agency(Long id) {
        this.id = id;
    }
}
