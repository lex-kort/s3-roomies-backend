package nl.fontys.s3.studenthousing.persistence.converter;

import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.interfaces.ObjectConverter;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

public class ListingConverterImpl implements ObjectConverter<ListingEntity, Listing> {
    public Listing convert(ListingEntity listing){
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