package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.domain.Listing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingManagerImplTest {
    @Mock
    private ListingRepository mockRepo;

    @InjectMocks
    private ListingManagerImpl listingManager;

    @Test
    void getActiveListings() {
        List<Listing> expected = List.of(Listing.builder().id(1L).isActive(true).build());
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).isActive(true).build()));

        List<Listing> actual = listingManager.getActiveListings();

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnMinSurfaceArea() {
        double minArea = 16;
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).surfaceArea(minArea).build(), Listing.builder().id(2L).surfaceArea(minArea-1).build()));

        List<Listing> actual = listingManager.getFilteredListings(minArea, null, null, null);
        List<Listing> expected = List.of(Listing.builder().id(1L).surfaceArea(minArea).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnMaxRent() {
        double maxRent = 306;
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).rent(maxRent).build(), Listing.builder().id(2L).rent(maxRent+1).build()));

        List<Listing> actual = listingManager.getFilteredListings(null, maxRent, null, null);
        List<Listing> expected = List.of(Listing.builder().id(1L).rent(maxRent).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnPetsAllowed() {
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).petsAllowed(true).build(), Listing.builder().id(2L).petsAllowed(false).build()));

        List<Listing> actual = listingManager.getFilteredListings(null, null, true, null);
        List<Listing> expected = List.of(Listing.builder().id(1L).petsAllowed(true).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getFilteredListings_filterOnNeighborhood() {
        String neighborhood = "Strijp";
        String differentNeighborhood = "Woensel";
        when(mockRepo.getActiveListings()).thenReturn(List.of(Listing.builder().id(1L).neighborhood(neighborhood).build(), Listing.builder().id(2L).neighborhood(differentNeighborhood).build()));

        List<Listing> actual = listingManager.getFilteredListings(null, null, null, neighborhood);
        List<Listing> expected = List.of(Listing.builder().id(1L).neighborhood(neighborhood).build());

        assertEquals(actual, expected);
        verify(mockRepo).getActiveListings();
    }

    @Test
    void getListing_validID() {
        Long id = 1L;
        Listing expectedListing = Listing.builder()
                .id(id)
                .address("Coolstreet 1 a")
                .city("Eindhoven")
                .description("very cool room")
                .neighborhood("Neigh")
                .surfaceArea(15d)
                .rent(300.50)
                .isActive(true)
                .petsAllowed(true)
                .build();

        when(mockRepo.getById(id)).thenReturn(expectedListing);

        Listing actualListing = listingManager.getListing(id);

        assertEquals(expectedListing.getId(), actualListing.getId());
        assertEquals(Listing.class, actualListing.getClass());
        verify(mockRepo).getById(id);
    }

    @Test
    void getListing_invalidID() {
        Long id = 100L;
        when(mockRepo.getById(100L)).thenThrow(InvalidListingIDException.class);

        assertThrows(InvalidListingIDException.class, () -> {
            listingManager.getListing(id);
        });
        verify(mockRepo).getById(id);
    }
}