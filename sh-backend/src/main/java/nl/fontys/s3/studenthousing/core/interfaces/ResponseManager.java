package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.Response;

import java.util.List;

public interface ResponseManager {
    Response respondToListing(Long listingId, Long userId);
    void deleteResponse(Long listingId, Long userId);
    List<Response> getListingResponses(Long listingId, Long userId);
    List<Response> getUserResponses(Long userId);
    Long getTotalResponses(Long listingId);
}
