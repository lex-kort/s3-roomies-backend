package nl.fontys.s3.studenthousing.business;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.core.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.core.interfaces.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListingManagerImpl implements ListingManager {
    private final ListingRepository repository;

    @Override
    public List<Listing> getActiveListings(){
        return repository.getActiveListings();
    }

    @Override
    public List<Listing> getFilteredListings(Double minArea, Double maxRent, Boolean petsAllowed, String neighborhood){
        List<Listing> listings = repository.getActiveListings();
        return listings.stream()
                .filter(listing -> filterListing(listing, minArea, maxRent, petsAllowed, neighborhood))
                .toList();
    }

    @Override
    public void createListing(Listing listing){
        repository.createListing(listing);
    }

    private boolean filterListing(Listing listing, Double minArea, Double maxRent, Boolean petsAllowed, String neighborhood) {
        return (minArea == null || listing.getSurfaceArea() >= minArea) &&
                (maxRent == null || listing.getRent() <= maxRent) &&
                (petsAllowed == null || listing.getPetsAllowed().equals(petsAllowed)) &&
                (neighborhood == null || listing.getNeighborhood().equalsIgnoreCase(neighborhood));
    }

    @Override
    public Listing getListing(Long id) {
        return repository.findById(id);
    }
}
