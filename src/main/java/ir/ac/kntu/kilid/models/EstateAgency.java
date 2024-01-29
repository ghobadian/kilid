package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "estateAgency")
public class EstateAgency {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private int numberOfEmployees;
    private byte[] logo;
//    @OneToOne
//    @JoinColumn(name = "city")
//    private City city;
    @OneToOne
    @JoinColumn(name = "estateAgency", unique = true)
    private Manager manager;

}
