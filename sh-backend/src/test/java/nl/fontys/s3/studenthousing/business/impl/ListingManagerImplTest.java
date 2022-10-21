package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.MockListingRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListingManagerImplTest {
    private ListingManagerImpl m;
    private List<Listing> listings;

    @BeforeEach
    void Init(){
        m = new ListingManagerImpl(new MockListingRepositoryImpl());
    }

    void AssertListingTypeAndActive(Listing l){
        Assertions.assertEquals(Listing.class, l.getClass());
        Assertions.assertTrue(l.getIsActive());
    }

    @Test
    void getActiveListings() {
        Init();

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
            Assertions.assertTrue(l.getPetsAllowed() == petsAllowed);
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

    @Test
    void getListing_validID(){
        Init();
        long id = 1;

        Listing listing = m.getListing(id);

        Assertions.assertEquals(id, listing.getId());
        Assertions.assertEquals(Listing.class, listing.getClass());
    }

    @Test
    void getListing_invalidID(){
        Init();
        long id = 100;

        Listing listing = m.getListing(id);

        Assertions.assertEquals(id, listing.getId());
        Assertions.assertEquals(Listing.class, listing.getClass());
    }
}