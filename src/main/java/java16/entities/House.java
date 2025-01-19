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

public class House{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "house_gen", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private int price;
    private double rating;
    private String description;
    private int room;
    private boolean furniture;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Rent_Info> rentInfos;

    public House(Long id) {
        this.id = id;
    }

    public House( HouseType houseType, int price, double rating,
                 String description, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }

}
