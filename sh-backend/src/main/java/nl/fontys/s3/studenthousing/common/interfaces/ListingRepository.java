package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.common.domain.Listing;

import java.util.List;

public interface ListingRepository {
    List<Listing> getActiveListings();

    void createListing(Listing listing);

    Listing getById(Long id);
}
