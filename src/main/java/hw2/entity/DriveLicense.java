package hw2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name = "driver_license")
public class DriveLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "series")
    private String series;

    @OneToOne(mappedBy = "driveLicense")
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public DriveLicense(String series) {
        this.series = series;
    }
}
