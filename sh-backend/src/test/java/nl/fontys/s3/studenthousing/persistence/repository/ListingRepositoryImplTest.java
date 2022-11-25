package nl.fontys.s3.studenthousing.persistence.repository;

import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingJPA;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.ListingRepositoryImpl;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingRepositoryImplTest {
    @Mock
    private ListingJPA mockJPA;
    @InjectMocks
    private ListingRepositoryImpl listingRepository;

    @Test
    void getActiveListings_allActive(){
        List<ListingEntity> entities = List.of(ListingEntity.builder().id(1L).isActive(true).build());
        List<Listing> expected = List.of(Listing.builder().id(1L).isActive(true).build());

        when(mockJPA.findByIsActiveTrue()).thenReturn(entities);

        List<Listing> actual = listingRepository.getActiveListings();

        assertEquals(actual, expected);
        verify(mockJPA).findByIsActiveTrue();
    }

    @Test
    void getListing_validID() {
        Long id = 1L;
        ListingEntity listingEntity = ListingEntity.builder()
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

        Listing expectedListing = Listing.builder()
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

        when(mockJPA.findById(id)).thenReturn(Optional.ofNullable(listingEntity));

        Listing actualListing = listingRepository.getById(id);

        assertEquals(expectedListing, actualListing);
        assertEquals(Listing.class, actualListing.getClass());
        verify(mockJPA).findById(id);
    }

    @Test
    void getListing_invalidID() {
        Long id = 100L;
        when(mockJPA.findById(100L)).thenReturn(Optional.empty());

        assertThrows(InvalidListingIDException.class, () -> {
            listingRepository.getById(id);
        });
        verify(mockJPA).findById(id);
    }
}