package nl.fontys.s3.studenthousing.controller.converter;

import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.interfaces.ObjectConverter;
import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;

public class ListingConverterImpl implements ObjectConverter<Listing, ListingDTO> {
    public ListingDTO convert(Listing listing){
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
}