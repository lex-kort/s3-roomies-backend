package nl.fontys.s3.studenthousing.persistence.impl;

import nl.fontys.s3.studenthousing.persistence.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MockListingRepositoryImpl implements ListingRepository {
    private static final int RANGE = 15;
    private static long NEXT_ID = 1;

    private final List<ListingEntity> listingEntities;

    public MockListingRepositoryImpl() {
        this.listingEntities = new ArrayList<>();
    }

    @Override
    public List<ListingEntity> getActiveListings(int offset) {
        return listingEntities.subList(offset, offset + RANGE);
    }

    @Override
    public Optional<ListingEntity> getById(long id) {
        return listingEntities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }
}
