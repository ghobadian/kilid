package ir.ac.kntu.kilid.models;

import ir.ac.kntu.kilid.models.output.AddressOutputDTO;
import ir.ac.kntu.kilid.models.output.EstateAgencyOutputDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdvertisementOutputDTO {
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
    private String fullAddress;
    private int area;
    private int year;
    private int rooms;
    private Date creationTime;
    private List<HouseFeature> houseFeatures;
    private AddressOutputDTO address;
    private EstateAgencyOutputDTO estateAgency;

    public static AdvertisementOutputDTO from(Advertisement advertisement) {
        return AdvertisementOutputDTO.builder()
                .useType(advertisement.getUseType())
                .area(advertisement.getArea())
                .negotiable(advertisement.isNegotiable())
                .fullMortgage(advertisement.isFullMortgage())
                .useType(advertisement.getUseType())
                .advertisementType(advertisement.getAdvertisementType())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .creationTime(advertisement.getCreationTime())
                .fullAddress(advertisement.getAddress())
                .area(advertisement.getArea())
                .year(advertisement.getYear())
                .rooms(advertisement.getRooms())
                .houseFeatures(advertisement.getHouseFeatures())
                .address(AddressOutputDTO.from(advertisement.getDistrict()))
                .estateAgency(EstateAgencyOutputDTO.from(advertisement.getEstateAgency()))
                .price(advertisement.getPrice())
                .mortgage(advertisement.getMortgage())
                .rent(advertisement.getRent())
                .build();
    }
}
