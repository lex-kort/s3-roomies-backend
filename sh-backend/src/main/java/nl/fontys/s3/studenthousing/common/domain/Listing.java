package nl.fontys.s3.studenthousing.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
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

    public boolean checkNeighborhood(String neighborhood){
        return this.neighborhood.equalsIgnoreCase(neighborhood);
    }
}
