package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.Listing;

import java.util.List;

public interface ListingManager {
    List<Listing> getActiveListings();
    List<Listing> getFilteredListings(Double minArea, Double maxRent, Boolean petsAllowed, String neighborhood);
    Listing getListing(Long id);
    void createListing(Listing listing);
}
