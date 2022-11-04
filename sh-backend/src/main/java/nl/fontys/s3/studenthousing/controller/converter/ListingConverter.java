package nl.fontys.s3.studenthousing.controller.converter;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;

@AllArgsConstructor
public class ListingConverter {
    public static ListingDTO convertToDTO(Listing listing){
        return ListingDTO.builder()
                .id(listing.getId())
                .address(listing.getAddress())
                .city(listing.getCity())
                .description(listing.getDescription())
                .neighborhood(listing.getNeighborhood())
                .surfaceArea(listing.getSurfaceArea())
                .rent(listing.getRent())
                .petsAllowed(listing.getPetsAllowed())
                .isActive(listing.getIsActive())
                .build();
    }
    public static Listing convertToDomain(ListingDTO listing){
        return Listing.builder()
                .id(listing.getId())
                .address(listing.getAddress())
                .city(listing.getCity())
                .description(listing.getDescription())
                .neighborhood(listing.getNeighborhood())
                .surfaceArea(listing.getSurfaceArea())
                .rent(listing.getRent())
                .petsAllowed(listing.getPetsAllowed())
                .isActive(listing.getIsActive())
                .build();
    }
}