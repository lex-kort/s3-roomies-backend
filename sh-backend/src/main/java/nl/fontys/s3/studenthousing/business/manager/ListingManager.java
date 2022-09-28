package nl.fontys.s3.studenthousing.business.manager;

import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.request.GetActiveListingsRequest;
import nl.fontys.s3.studenthousing.domain.response.GetActiveListingsResponse;

import java.util.Optional;

public interface ListingManager {
    GetActiveListingsResponse getActiveListings(GetActiveListingsRequest request);
    Optional<Listing> getListing(long id);
}
