package ir.ac.kntu.kilid.models.output;

import ir.ac.kntu.kilid.models.District;
import lombok.Builder;

@Builder
public class AddressOutputDTO {
    String city;
    String district;
    String province;
    public static AddressOutputDTO from(District district) {
        return AddressOutputDTO.builder()
                .city(district.getCity().getName())
                .district(district.getName())
                .province(district.getCity().getProvince().getName())
                .build();
    }
}
