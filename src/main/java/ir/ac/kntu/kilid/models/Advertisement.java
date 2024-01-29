package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Advertisement {
    @Id
    private Long id;
    private String title;
    private int mortgage;
    private int rent;
    private int buy;
    private byte[] main_image;
    private byte[] image2;
    private byte[] image3;
    private byte[] image4;
    private byte[] image5;
    private String description;
    private int exchange;
    private int collaborative;
    private boolean convertable;
    private boolean preSell;
    private int administrativePosition;
    private int borrower;
    private boolean newlyBuilt;
    private int inProportionateShare;
    @OneToOne
    private File file;
}
