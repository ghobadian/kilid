package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstateAgency {
    @Id
    @GeneratedValue
    @Column(name = "estate_agency_id")
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private int numberOfEmployees;
    private byte[] logo;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private City city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
