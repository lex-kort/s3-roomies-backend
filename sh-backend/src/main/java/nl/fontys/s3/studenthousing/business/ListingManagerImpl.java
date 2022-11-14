package nl.fontys.s3.studenthousing.business;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.common.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.common.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.interfaces.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListingManagerImpl implements ListingManager {
    private final ListingRepository repository;

    @Override
    public List<Listing> getActiveListings(){
        return repository.getActiveListings();
    }

    @Override
    public List<Listing> getFilteredListings(Integer minArea, Double maxRent, Boolean petsAllowed, String neighborhood){
        List<Listing> listings = repository.getActiveListings();
        return listings.stream()
                .filter(Listing::getIsActive)
                .filter(listing -> filterListing(listing, minArea, maxRent, petsAllowed, neighborhood))
                .toList();
    }

    @Override
    public void createListing(Listing listing){
        repository.createListing(listing);
    }

    private boolean filterListing(Listing listing, Integer minArea, Double maxRent, Boolean petsAllowed, String neighborhood) {
        return (minArea == null || listing.getSurfaceArea() >= minArea) &&
                (maxRent == null || listing.getRent() <= maxRent) &&
                (petsAllowed == null || listing.getPetsAllowed() == petsAllowed) &&
                (neighborhood == null || listing.getNeighborhood().equalsIgnoreCase(neighborhood));
    }

    @Override
    public Listing getListing(Long id) {
        return repository.getById(id);
    }
}
