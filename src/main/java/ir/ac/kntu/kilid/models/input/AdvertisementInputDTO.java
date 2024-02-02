package ir.ac.kntu.kilid.models.input;

import ir.ac.kntu.kilid.models.AdvertisementType;
import ir.ac.kntu.kilid.models.UseType;
import lombok.Getter;

@Getter
public class AdvertisementInputDTO {
    private String title;
    private String description;
    private String district;
    private int area;
    private int price;
    private boolean isNegotiable;
    private int mortgage;
    private int rent;
    private boolean fullMortgage;
    private UseType useType;
    private AdvertisementType advertisementType;
}
