package nl.fontys.s3.studenthousing.persistence.converter;

import lombok.NoArgsConstructor;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

@NoArgsConstructor
public class ListingConverter {
    public static ListingEntity convertToEntity(Listing listing){
        return ListingEntity.builder()
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
    public static Listing convertToDomain(ListingEntity listing){
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