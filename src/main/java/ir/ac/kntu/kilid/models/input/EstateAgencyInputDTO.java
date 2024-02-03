package ir.ac.kntu.kilid.models.input;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class EstateAgencyInputDTO {
    @NotNull
    private String persianName;
    private String phone;
    @NotNull
    private String city;
    private Integer numberOfEmployees;
    private String managerUsername;
    @NotNull
    private String managerFirstName;
    private String managerLastName;
    @NotNull
    private String managerPhoneNumber;
    @NotNull
    private String password;
}
