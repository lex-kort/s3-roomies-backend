package nl.fontys.s3.studenthousing.business.manager.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.business.converter.ListingConverter;
import nl.fontys.s3.studenthousing.business.manager.ListingManager;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.request.GetActiveListingsRequest;
import nl.fontys.s3.studenthousing.domain.response.GetActiveListingsResponse;
import nl.fontys.s3.studenthousing.persistence.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListingManagerImpl implements ListingManager {
    private final ListingRepository repository;


    @Override
    public GetActiveListingsResponse getActiveListings(GetActiveListingsRequest request) {
        List<ListingEntity> results = repository.getActiveListings(0);

        final GetActiveListingsResponse response = new GetActiveListingsResponse();
        response.setListings(results.stream()
                .map(ListingConverter::convert)
                .toList());

        return response;
    }

    @Override
    public Optional<Listing> getListing(long id) {
        Optional<Listing> listing = repository.getById(id).map(ListingConverter::convert);
        return listing;
    }
}
