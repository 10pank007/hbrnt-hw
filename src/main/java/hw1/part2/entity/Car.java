package hw1.part2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model")
    private String model;
    @Enumerated(EnumType.STRING)
    private TYPE type;
    @Column(name = "price")
    private Double price;
    @Column(name = "year")
    private int year;

    public Car(String model, TYPE type, Double price, int year) {
        this.model = model;
        this.type = type;
        this.price = price;
        this.year = year;
    }
}
