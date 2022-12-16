package nl.fontys.s3.studenthousing.domain;

import lombok.*;
import nl.fontys.s3.studenthousing.core.enums.OrderType;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Listing {
    private Long id;
    private Long ownerId;
    @Setter
    private String address;
    @Setter
    private String city;
    @Setter
    private String neighborhood;
    @Setter
    private String description;
    @Setter
    private Integer surfaceArea; // In square meters
    @Setter
    private Double rent; // In Euro's
    @Setter
    private Boolean petsAllowed;
    @Setter
    private OrderType orderType;
    @Setter
    private Boolean isActive;
}
