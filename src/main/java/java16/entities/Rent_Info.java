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
public class Rent_Info extends BaseEntity {

    private LocalDate checkIn;
    private LocalDate checkOut;
    @ManyToOne
    private Customer customer;
     @ManyToOne
    private Owner owner;
    @OneToOne
    private House house;
    @ManyToOne
    private Agency agency;

    public Rent_Info(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
