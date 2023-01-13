package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.Response;

import java.util.List;

public interface ResponseRepository {
    Response findResponse(Long listingId, Long userId);
    Response respondToListing(Response response);
    void deleteResponse(Long responseId);
    List<Response> getListingResponses(Long listingId);
    List<Response> getUserResponses(Long userId);
    Long getTotalResponses(Long listingId);
}
