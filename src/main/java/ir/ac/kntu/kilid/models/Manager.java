package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "estateAgency", unique = true)
    private EstateAgency estateAgency;
    @OneToOne
    @JoinColumn(name = "user", unique = true)
    private User user;
}
