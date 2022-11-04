package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.common.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListingManagerImplTest {
    @Mock
    private ListingRepository mockRepo;
    @InjectMocks
    private ListingManagerImpl m;

    private List<Listing> listings;

    @BeforeEach
    void Init(){
    }

    void AssertListingTypeAndActive(Listing l){
        Assertions.assertEquals(Listing.class, l.getClass());
        Assertions.assertTrue(l.getIsActive());
    }

    @Test
    void getActiveListings() {
        listings = m.getActiveListings();
        for(Listing l : listings){
            AssertListingTypeAndActive(l);
        }
    }

    @Test
    void getFilteredListings_filterOnMinSurfaceArea() {
        Init();
        int minArea = 16;

        listings = m.getFilteredListings(minArea, null, null, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getSurfaceArea() >= minArea);
        }
    }

    @Test
    void getFilteredListings_filterOnMaxRent() {
        Init();
        double maxRent = 306;

        listings = m.getFilteredListings(null, maxRent, null, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getSurfaceArea() <= maxRent);
        }
    }

    @Test
    void getFilteredListings_filterOnPetsAllowed() {
        Init();
        boolean petsAllowed = false;

        listings = m.getFilteredListings(null, null, petsAllowed, null);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertEquals(petsAllowed, l.getPetsAllowed());
        }
    }

    @Test
    void getFilteredListings_filterOnNeighborhood() {
        Init();
        String neighborhood = "Neigh";

        listings = m.getFilteredListings(null, null, null, neighborhood);

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getNeighborhood().equalsIgnoreCase(neighborhood));
            Assertions.assertTrue(l.checkNeighborhood(neighborhood));
        }
    }

    void getListing_validID(){
        Init();

        ListingEntity firstListing = ListingEntity.builder()
                .address("Coolstreet 1 a")
                .city("Eindhoven")
                .description("very cool room")
                .neighborhood("Neigh")
                .surfaceArea(15)
                .rent(300.50)
                .isActive(true)
                .petsAllowed(true)
                .build();

        ListingEntity secondListing = ListingEntity.builder()
                .address("Coolstreet 1 b")
                .city("Eindhoven")
                .description("another very cool room")
                .neighborhood("Neigh")
                .surfaceArea(16)
                .rent(302.25)
                .isActive(true)
                .petsAllowed(true)
                .build();

        ListingEntity thirdListing = ListingEntity.builder()
                .address("Coolstreet 1 c")
                .city("Eindhoven")
                .description("this room is taken (and also very cool)")
                .neighborhood("Neigh")
                .surfaceArea(13)
                .rent(298.00)
                .isActive(false)
                .petsAllowed(true)
                .build();

        mockRepo.save(firstListing);
        mockRepo.save(secondListing);
        mockRepo.save(thirdListing);

        Listing listing = m.getListing(firstListing.getId());

        Assertions.assertEquals(firstListing.getId(), listing.getId());
        Assertions.assertEquals(Listing.class, listing.getClass());
    }

    @Test
    void getListing_invalidID(){
        Init();
        long id = 100;

        Assertions.assertThrows(InvalidListingIDException.class, () -> {
            m.getListing(id);
        });
    }
}