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
        listingEntities.add(ListingEntity.builder()
            .id(NEXT_ID++)
            .address("Coolstreet 1 a")
            .city("Eindhoven")
            .description("very cool house")
            .neighborhood("Neigh")
            .surfaceArea(15)
            .rent(300.50)
            .isActive(true)
            .build());

        listingEntities.add(ListingEntity.builder()
            .id(NEXT_ID++)
            .address("Coolstreet 1 b")
            .city("Eindhoven")
            .description("cool house")
            .neighborhood("Neigh")
            .surfaceArea(15)
            .rent(300.50)
            .isActive(false)
            .build());

        listingEntities.add(ListingEntity.builder()
            .id(NEXT_ID++)
            .address("Coolstreet 1 c")
            .city("Eindhoven")
            .description("great house")
            .neighborhood("Neigh")
            .surfaceArea(15)
            .rent(300.50)
            .isActive(true)
            .build());
    }

    @Override
    public List<ListingEntity> getActiveListings(int offset) {
        return listingEntities.stream().filter(ListingEntity::getIsActive).toList();
    }

    @Override
    public Optional<ListingEntity> getById(long id) {
        return listingEntities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }
}
