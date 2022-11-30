package nl.fontys.s3.studenthousing.persistence;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.converters.ListingConverter;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingJPA;
import nl.fontys.s3.studenthousing.core.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ListingRepositoryImpl implements ListingRepository {
    private final ListingJPA listingJPA;
    @Override
    public List<Listing> getActiveListings() {
        return listingJPA.findByIsActiveTrue().stream().map(ListingConverter::convertToDomain).toList();
    }
    @Override
    public void createListing(Listing listing){
        listingJPA.save(ListingConverter.convertToEntity(listing));
    }

    @Override
    public Listing getById(Long id){
        Optional<ListingEntity> optionalListing;
        try{
            optionalListing = listingJPA.findById(id).stream()
                    .filter(entity -> entity.getId().equals(id))
                    .findFirst();
        }
        catch(IllegalArgumentException e){
            throw new InvalidListingIDException();
        }
        if(optionalListing.isEmpty()) {
            throw new InvalidListingIDException();
        }
        return ListingConverter.convertToDomain(optionalListing.get());
    }
}
