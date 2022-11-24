package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingManagerImplTest {


    @Mock
    private ListingRepository mockRepo;

    @InjectMocks
    private ListingManagerImpl listingManager;

    private List<Listing> listings;


    private void AssertListingTypeAndActive(Listing l) {
        assertEquals(Listing.class, l.getClass());
        Assertions.assertTrue(l.getIsActive());
    }

    @Test
    void getActiveListings() {
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).build()));

        List<Listing> actual = listingManager.getActiveListings();
        List<Listing> expected = List.of(Listing.builder().id(1L).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnMinSurfaceArea() {
        int minArea = 16;
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).surfaceArea(minArea).build(), Listing.builder().id(1L).surfaceArea(minArea-1).build()));

        List<Listing> actual = listingManager.getFilteredListings(minArea, null, null, null);
        List<Listing> expected = List.of(Listing.builder().id(1L).surfaceArea(minArea).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnMaxRent() {
        double maxRent = 306;

        listings = listingManager.getFilteredListings(null, maxRent, null, null);

        for (Listing l : listings) {
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getSurfaceArea() <= maxRent);
        }
    }

    @Test
    void getFilteredListings_filterOnPetsAllowed() {
        boolean petsAllowed = false;

        listings = listingManager.getFilteredListings(null, null, petsAllowed, null);

        for (Listing l : listings) {
            AssertListingTypeAndActive(l);
            assertEquals(petsAllowed, l.getPetsAllowed());
        }
    }

    @Test
    void getFilteredListings_filterOnNeighborhood() {
        String neighborhood = "Neigh";

        listings = listingManager.getFilteredListings(null, null, null, neighborhood);

        for (Listing l : listings) {
            AssertListingTypeAndActive(l);
            Assertions.assertTrue(l.getNeighborhood().equalsIgnoreCase(neighborhood));
            Assertions.assertTrue(l.checkNeighborhood(neighborhood));
        }
    }

//    @Test
//    void getListing_validID() {
//        Long id = 1L;
//
//        Listing newListing = Listing.builder()
//                .id(id)
//                .address("Coolstreet 1 a")
//                .city("Eindhoven")
//                .description("very cool room")
//                .neighborhood("Neigh")
//                .surfaceArea(15)
//                .rent(300.50)
//                .isActive(true)
//                .petsAllowed(true)
//                .build();
//
//        ListingEntity newEntity = ListingEntity.builder()
//                .id(newListing.getId())
//                .address(newListing.getAddress())
//                .city(newListing.getCity())
//                .description(newListing.getDescription())
//                .neighborhood(newListing.getNeighborhood())
//                .surfaceArea(newListing.getSurfaceArea())
//                .rent(newListing.getRent())
//                .isActive(newListing.getIsActive())
//                .petsAllowed(newListing.getPetsAllowed())
//                .build();
//
//        listingManager.createListing(newListing);
//        when(mockJPA.findById(newListing.getId())).thenReturn(Optional.ofNullable(newEntity));
//
//        Listing listing = listingManager.getListing(newListing.getId());
//
//        assertEquals(newListing.getId(), listing.getId());
//        assertEquals(Listing.class, listing.getClass());
//    }
//
//    @Test
//    void getListing_invalidID() {
//        long id = 100;
//
//        Assertions.assertThrows(InvalidListingIDException.class, () -> {
//            listingManager.getListing(id);
//        });
//    }
}