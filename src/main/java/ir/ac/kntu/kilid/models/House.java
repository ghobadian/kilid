package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class House {
    @Id
    private Long id;
    private String address;
    private int area;
    private int year;
    private int rooms;
    private Date creationTime;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = HouseFeature.class)
    private List<HouseFeature> features;
    @OneToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;
    @OneToOne
    @JoinColumn(name = "district")
    private District district;


}
