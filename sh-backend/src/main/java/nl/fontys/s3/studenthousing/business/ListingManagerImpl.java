package nl.fontys.s3.studenthousing.business;

import nl.fontys.s3.studenthousing.common.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.common.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.interfaces.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingManagerImpl implements ListingManager {
    private final ListingRepository repository;

    private List<Listing> listings;

    public ListingManagerImpl(ListingRepository repository){
        this.repository = repository;
        listings = this.repository.load();
    }

    @Override
    public List<Listing> getActiveListings(Integer minArea, Double maxRent, Boolean petsAllowed, String neighborhood){
        if(minArea == null && maxRent == null && petsAllowed == null && neighborhood == null){
            return listings.stream()
                    .filter(Listing::getIsActive)
                    .toList();
        }
        else{
            return listings.stream()
                    .filter(Listing::getIsActive)
                    .filter(listing -> filterListing(listing, minArea, maxRent, petsAllowed, neighborhood))
                    .toList();
        }
    }

    private boolean filterListing(Listing listing, Integer minArea, Double maxRent, Boolean petsAllowed, String neighborhood) {
        return (minArea == null || listing.getSurfaceArea() >= minArea) &&
                (maxRent == null || listing.getRent() <= maxRent) &&
                (petsAllowed == null || listing.getPetsAllowed() == petsAllowed) &&
                (neighborhood == null || listing.getNeighborhood().equalsIgnoreCase(neighborhood));
    }

    @Override
    public Listing getListing(long id) {
        Optional<Listing> optionalListing = listings.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
        if(optionalListing.isEmpty()) throw new InvalidListingIDException();
        return optionalListing.get();
    }
}
