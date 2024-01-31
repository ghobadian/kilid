package ir.ac.kntu.kilid.models;

import ir.ac.kntu.kilid.models.input.AdvertisementInputDTO;
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
public class Advertisement {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String title;
    private AdvertisementType advertisementType;
    private int price;
    private int mortgage;
    private boolean fullMortgage;
    private boolean negotiable;
    private int rent;
    private String description;
    private UseType useType;
    private String address;
    private int area;
    private int year;
    private int rooms;
    private Date creationTime;
    @ElementCollection(targetClass = HouseFeature.class)
    private List<HouseFeature> houseFeatures;
    @OneToOne(cascade = CascadeType.ALL)
    private District district;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estate_agency_id")
    private EstateAgency estateAgency;


    public static Advertisement from(AdvertisementInputDTO input) {
        return Advertisement.builder()
                .useType(input.getUseType())
                .area(input.getArea())
                .negotiable(input.isNegotiable())
                .fullMortgage(input.isFullMortgage())
                .useType(input.getUseType())
                .advertisementType(input.getAdvertisementType())
                .build();
    }
}
