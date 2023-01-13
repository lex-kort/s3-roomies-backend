package nl.fontys.s3.studenthousing.core.converters;

import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;
import nl.fontys.s3.studenthousing.domain.account.Landlord;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

public class ListingConverter {
    private ListingConverter(){}

    public static ListingDTO convertToDTO(Listing listing){
        return ListingDTO.builder()
                .id(listing.getId())
                .ownerId(listing.getOwner().getId())
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
    public static Listing convertToDomain(ListingDTO listing, Landlord owner){
        return Listing.builder()
                .id(listing.getId())
                .owner(owner)
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
                .owner((Landlord)UserConverter.convertToDomain(listing.getOwner()))
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
}