package nl.fontys.s3.studenthousing.persistence.repository;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.common.interfaces.ListingJPA;
import nl.fontys.s3.studenthousing.common.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.converter.ListingConverter;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ListingRepositoryImpl implements ListingRepository {
    private ListingJPA listingJPA;
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
        Optional<ListingEntity> optionalListing = listingJPA.findById(id).stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
        if(optionalListing.isEmpty()) throw new InvalidListingIDException();
        return ListingConverter.convertToDomain(optionalListing.get());
    }
}
