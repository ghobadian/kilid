package ir.ac.kntu.kilid.models.filters;

import ir.ac.kntu.kilid.models.AdvertisementType;
import ir.ac.kntu.kilid.models.HouseFeature;
import ir.ac.kntu.kilid.models.UseType;
import lombok.Getter;

import java.util.List;

@Getter
public class AdvertisementFilter {
    AdvertisementType advertisementType;
    UseType useType;
    Integer rooms;
    Integer area;
    Integer year;
    Integer price;
    Integer rent;
    Integer mortgage;
    List<HouseFeature> houseFeatures;

}
