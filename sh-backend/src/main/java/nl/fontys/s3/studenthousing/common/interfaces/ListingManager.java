package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.common.domain.Listing;

import java.util.List;

public interface ListingManager {
    List<Listing> getActiveListings();
    List<Listing> getFilteredListings(Integer minArea, Double maxRent, Boolean petsAllowed, String neighborhood);

    Listing getListing(long id);
}
