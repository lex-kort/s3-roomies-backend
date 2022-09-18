package nl.fontys.s3.studenthousing.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListingEntity {
    private Long id;
    private String address;
    private String city;
    private String neighborhood;
    private String description;
    private Integer surfaceArea; // In square meters
    private Double rent; // In Euro's
    private Boolean isActive;
}
