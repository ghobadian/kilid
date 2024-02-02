package ir.ac.kntu.kilid.models.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.ac.kntu.kilid.models.AdvertisementType;
import ir.ac.kntu.kilid.models.HouseFeature;
import ir.ac.kntu.kilid.models.UseType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude
public class AdvertisementFilter {
    private AdvertisementType advertisementType;
    private UseType useType;
    private Integer rooms;
    private Integer area;
    private Integer year;
    private Integer price;
    private Integer rent;
    private Integer mortgage;
}
