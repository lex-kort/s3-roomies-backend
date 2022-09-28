package nl.fontys.s3.studenthousing.persistence;

import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

import java.util.List;
import java.util.Optional;

public interface ListingRepository {

    List<ListingEntity> getActiveListings(int offset);

    Optional<ListingEntity> getById(long id);
}
