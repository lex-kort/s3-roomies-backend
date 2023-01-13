package nl.fontys.s3.studenthousing.business;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseManager;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseRepository;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.Response;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseManagerImpl implements ResponseManager {
    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    @Override
    public Response respondToListing(Long listingId, Long userId) {
        Listing listingExists = listingRepository.findById(listingId);
        if(listingExists == null) throw new InvalidListingIDException();

        User userExists = userRepository.findById(userId);
        if(userExists == null) throw new InvalidCredentialsException();

        Response responseExists = responseRepository.findResponse(listingId, userId);
        if(responseExists != null) throw new IllegalArgumentException();

        LocalDateTime currentTime = LocalDateTime.now();

        Response response = Response.builder()
                .user(userExists)
                .listing(listingExists)
                .responseDate(currentTime)
                .build();
        return responseRepository.respondToListing(response);
    }

    @Override
    public void deleteResponse(Long listingId, Long userId) {
        Response response = responseRepository.findResponse(listingId, userId);
        if(response == null) throw new IllegalArgumentException();
        responseRepository.deleteResponse(response.getId());
    }

    @Override
    public List<Response> getListingResponses(Long listingId, Long userId) {
        Listing listing = listingRepository.findById(listingId);
        if(listing == null) throw new InvalidListingIDException();
        if(!listing.getOwner().getId().equals(userId)) throw new InvalidCredentialsException();
        return responseRepository.getListingResponses(listingId);
    }

    @Override
    public List<Response> getUserResponses(Long userId) {
        return responseRepository.getUserResponses(userId);
    }

    @Override
    public Long getTotalResponses(Long listingId){
        return responseRepository.getTotalResponses(listingId);
    }
}
