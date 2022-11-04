package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<ListingEntity, Long> {
    List<ListingEntity> findByIsActiveTrue();
}
