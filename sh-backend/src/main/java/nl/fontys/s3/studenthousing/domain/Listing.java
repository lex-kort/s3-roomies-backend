package nl.fontys.s3.studenthousing.domain;

import lombok.*;
import nl.fontys.s3.studenthousing.core.enums.OrderType;
import nl.fontys.s3.studenthousing.domain.account.Landlord;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Listing {
    private Long id;
    private Landlord owner;
    @Setter
    private String address;
    @Setter
    private String city;
    @Setter
    private String neighborhood;
    @Setter
    private String description;
    @Setter
    private Double surfaceArea; // In square meters
    @Setter
    private Double rent; // In Euro's
    @Setter
    private Boolean petsAllowed;
    @Setter

    private OrderType orderType;
    @Setter
    private Boolean isActive;
}
