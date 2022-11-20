package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingJPA;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import nl.fontys.s3.studenthousing.persistence.repository.ListingRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingManagerImplTest {
    @Mock
    private ListingJPA mockJPA;

    @InjectMocks
    private ListingRepositoryImpl mockRepo;

    private ListingManagerImpl listingManager;

    private List<Listing> listings;

    @BeforeEach
    void initialize(){
        listingManager = new ListingManagerImpl(mockRepo);
    }

    void AssertListingTypeAndActive(Listing l){
        Assertions.assertEquals(Listing.class, l.getClass());
        Assertions.assertTrue(l.getIsActive());
    }

    @Test
    void getActiveListings() {
        listings = listingManager.getActiveListings();
        for(Listing l : listings){
            AssertListingTypeAndActive(l);
        }
    }

    @Test
    void getFilteredListings_filterOnMinSurfaceArea() {
        int minArea = 16;

        listings = listingManager.getFilteredListings(minArea, null, null, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getSurfaceArea() >= minArea);
        }
    }

    @Test
    void getFilteredListings_filterOnMaxRent() {
        double maxRent = 306;

        listings = listingManager.getFilteredListings(null, maxRent, null, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getSurfaceArea() <= maxRent);
        }
    }

    @Test
    void getFilteredListings_filterOnPetsAllowed() {
        boolean petsAllowed = false;

        listings = listingManager.getFilteredListings(null, null, petsAllowed, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertEquals(petsAllowed, l.getPetsAllowed());
        }
    }

    @Test
    void getFilteredListings_filterOnNeighborhood() {
        String neighborhood = "Neigh";

        listings = listingManager.getFilteredListings(null, null, null, neighborhood);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getNeighborhood().equalsIgnoreCase(neighborhood));
            Assertions.assertTrue(l.checkNeighborhood(neighborhood));
        }
    }

    @Test
    void getListing_validID(){
        Long id = 1L;

        Listing newListing = Listing.builder()
                .id(id)
                .address("Coolstreet 1 a")
                .city("Eindhoven")
                .description("very cool room")
                .neighborhood("Neigh")
                .surfaceArea(15)
                .rent(300.50)
                .isActive(true)
                .petsAllowed(true)
                .build();

        ListingEntity newEntity = ListingEntity.builder()
                .id(newListing.getId())
                .address(newListing.getAddress())
                .city(newListing.getCity())
                .description(newListing.getDescription())
                .neighborhood(newListing.getNeighborhood())
                .surfaceArea(newListing.getSurfaceArea())
                .rent(newListing.getRent())
                .isActive(newListing.getIsActive())
                .petsAllowed(newListing.getPetsAllowed())
                .build();

        listingManager.createListing(newListing);
        when(mockJPA.findById(newListing.getId())).thenReturn(Optional.ofNullable(newEntity));

        Listing listing = listingManager.getListing(newListing.getId());

        Assertions.assertEquals(newListing.getId(), listing.getId());
        Assertions.assertEquals(Listing.class, listing.getClass());
    }

    @Test
    void getListing_invalidID(){
        long id = 100;

        Assertions.assertThrows(InvalidListingIDException.class, () -> {
            listingManager.getListing(id);
        });
    }
}