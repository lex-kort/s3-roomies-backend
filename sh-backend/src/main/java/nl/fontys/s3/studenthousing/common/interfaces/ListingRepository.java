package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.common.domain.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingRepository {
    List<Listing> load();
    List<Listing> getActiveListings(String minArea, Double maxRent, Boolean petsAllowed, String neighborhood);
    Listing getById(long id);
}
