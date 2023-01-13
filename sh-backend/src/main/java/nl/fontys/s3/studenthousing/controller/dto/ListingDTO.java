package nl.fontys.s3.studenthousing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ListingDTO {
    private Long id;
    private Long ownerId;
    private String address;
    private String city;
    private String neighborhood;
    private String description;
    private Double surfaceArea; // In square meters
    private Double rent; // In Euro's
    private Boolean petsAllowed;
    private Boolean isActive;
}
