package nl.fontys.s3.studenthousing.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.fontys.s3.studenthousing.persistence.entity.account.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "responses")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "listing_id")
    private ListingEntity listing;
    @NotBlank
    @Column(name = "response_date")
    private Date responseDate;
    @Column(name = "position")
    private Integer position;
    @Column(name = "has_accepted")
    private Boolean hasAccepted;
}