package nl.fontys.s3.studenthousing.persistence.repository;

import nl.fontys.s3.studenthousing.core.interfaces.ListingJPA;
import nl.fontys.s3.studenthousing.domain.Listing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListingRepositoryImplTest {
    @Mock
    private ListingJPA listingJPA;

    @InjectMocks
    private ListingRepositoryImpl listingRepository;

    void AssertListingTypeAndActive(Listing l){
        Assertions.assertEquals(Listing.class, l.getClass());
        Assertions.assertTrue(l.getIsActive());
    }

    @Test
    public void getActiveListings_allActive(){
        List<Listing> listings = listingRepository.getActiveListings();

        for(Listing l : listings){
            AssertListingTypeAndActive(l);
        }
    }
}