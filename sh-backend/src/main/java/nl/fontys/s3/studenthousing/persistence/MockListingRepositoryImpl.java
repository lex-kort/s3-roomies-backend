package nl.fontys.s3.studenthousing.persistence;

import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.common.interfaces.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.converter.ListingConverterImpl;
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
    private final ListingConverterImpl listingConverter;

    public MockListingRepositoryImpl() {
        listingConverter = new ListingConverterImpl();

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
            .petsAllowed(true)
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
            .petsAllowed(true)
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
            .petsAllowed(false)
            .build());

        listingEntities.add(ListingEntity.builder()
            .id(NEXT_ID++)
            .address("Coolstreet 1 d")
            .city("Eindhoven")
            .description("great house")
            .neighborhood("Weigh")
            .surfaceArea(18)
            .rent(350.50)
            .isActive(true)
            .petsAllowed(false)
            .build());
    }

    @Override
    public List<Listing> load(){
        return listingEntities.stream()
                .map(listingConverter::convert)
                .toList();
    }

    @Override
    public List<Listing> getActiveListings(String minArea, Double maxRent, Boolean petsAllowed, String neighborhood) {
        return listingEntities.stream()
                .filter(ListingEntity::getIsActive)
                .map(listingConverter::convert)
                .toList();
    }

    @Override
    public Listing getById(long id) {
        Optional<Listing> optionalListing = listingEntities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .map(listingConverter::convert);
        if(optionalListing.isEmpty()) throw new InvalidListingIDException();
        return optionalListing.get();
    }
}
