package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.ListingManagerImpl;
import nl.fontys.s3.studenthousing.persistence.MockListingRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListingManagerImplTest {
    private ListingManagerImpl m;

    @BeforeEach
    void Init(){
        m = new ListingManagerImpl(new MockListingRepositoryImpl());
    }

    @Test
    void getActiveListings() {
        Init();
    }

    @Test
    void getListing_validID() {
        Init();
    }
}