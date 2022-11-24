package nl.fontys.s3.studenthousing.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Listing {
    private Long id;
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
    private Boolean isActive;
}
