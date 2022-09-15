package nl.fontys.s3.studenthousing.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Listing {
    private Long id;
    private String address;
    private String city;
    private String neighborhood;
    private String description;
    private Integer surfaceArea; // In square meters
    private Double rent; // In Euro's
}
