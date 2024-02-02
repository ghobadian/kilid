package ir.ac.kntu.kilid.models.output;

import ir.ac.kntu.kilid.models.EstateAgency;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstateAgencyOutputDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private int numberOfEmployees;
    private String managerUsername;
    public static EstateAgencyOutputDTO from(EstateAgency estateAgency) {
        return EstateAgencyOutputDTO.builder()
                .name(estateAgency.getName())
                .address(estateAgency.getAddress())
                .phoneNumber(estateAgency.getPhoneNumber())
                .numberOfEmployees(estateAgency.getNumberOfEmployees())
                .managerUsername(estateAgency.getManager().getUser().getUsername())
                .build();
    }
}
