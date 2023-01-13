package nl.fontys.s3.studenthousing.persistence;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.converters.ResponseConverter;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseJPA;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseRepository;
import nl.fontys.s3.studenthousing.domain.Response;
import nl.fontys.s3.studenthousing.persistence.entity.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResponseRepositoryImpl implements ResponseRepository {
    private final ResponseJPA responseJPA;

    @Override
    public Response findResponse(Long listingId, Long userId){
        Optional<ResponseEntity> response = responseJPA.findResponse(listingId, userId);
        return response.map(ResponseConverter::convertToDomain).orElse(null);
    }

    @Override
    public Response respondToListing(Response response) {
        ResponseEntity result = responseJPA.save(ResponseConverter.convertToEntity(response));
        return ResponseConverter.convertToDomain(responseJPA.findById(result.getId()).get());
    }

    @Override
    public void deleteResponse(Long responseId) {
        responseJPA.deleteById(responseId);
    }

    @Override
    public List<Response> getListingResponses(Long listingId) {
        return responseJPA.findAllByListingId(listingId)
                .stream()
                .map(ResponseConverter::convertToDomain)
                .toList();
    }

    @Override
    public List<Response> getUserResponses(Long userId) {
        return responseJPA.findAllByUserId(userId)
                .stream()
                .map(ResponseConverter::convertToDomain)
                .toList();
    }

    @Override
    public Long getTotalResponses(Long listingId) {
        return responseJPA.getTotalResponses(listingId);
    }
}
