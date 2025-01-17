package java16.entities;

import jakarta.persistence.*;
import java16.enums.HouseType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen", sequenceName = "house_gen", allocationSize = 1)
public class House extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    private String description;
    private int room;
    private boolean furniture;

    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;
}
