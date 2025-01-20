package java16.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "rent_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "id_gen", sequenceName = "rent_info_gen", allocationSize = 1)
public class Rent_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "rent_gen", allocationSize = 1)
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
     @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;
    @OneToOne(cascade = CascadeType.ALL)
    private House house;
    @ManyToOne(cascade = CascadeType.ALL)
    private Agency agency;

    public Rent_Info(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
