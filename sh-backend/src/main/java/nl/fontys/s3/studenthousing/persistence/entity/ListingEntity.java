package nl.fontys.s3.studenthousing.persistence.entity;

import lombok.*;

import nl.fontys.s3.studenthousing.persistence.entity.account.LandlordEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "listing")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "neighborhood")
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private LandlordEntity owner;

    @NotBlank
    @Length(min = 2, max = 500)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "surface_area")
    private Integer surfaceArea; // In square meters

    @NotBlank
    @Column(name = "rent")
    private Double rent; // In Euro's

    @NotBlank
    @Column(name = "pets_allowed")
    private Boolean petsAllowed;

    @NotBlank
    @Column(name = "order_type")
    private String orderType;

    @NotBlank
    @Column(name= "end_date")
    private Date endDate;

    @NotBlank
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "listing")
    private List<ResponseEntity> responses;
}
