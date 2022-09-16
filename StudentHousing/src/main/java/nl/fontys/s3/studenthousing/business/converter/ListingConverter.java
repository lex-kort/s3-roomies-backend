package nl.fontys.s3.studenthousing.business.converter;

import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

public class ListingConverter {

    public static Listing convert(ListingEntity listing){
        return Listing.builder()
                .id(listing.getId())
                .address(listing.getAddress())
                .city(listing.getCity())
                .neighborhood(listing.getNeighborhood())
                .surfaceArea(listing.getSurfaceArea())
                .rent(listing.getRent())
                .build();
    }
}
