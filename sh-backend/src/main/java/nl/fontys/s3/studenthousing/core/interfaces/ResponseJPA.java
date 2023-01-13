package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import nl.fontys.s3.studenthousing.persistence.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResponseJPA extends JpaRepository<ResponseEntity, Long>
{
    @Query(value = "SELECT * FROM responses WHERE listing_id = :listingId AND user_id = :userId LIMIT 1", nativeQuery = true)
    Optional<ResponseEntity> findResponse(Long listingId, Long userId);
    List<ResponseEntity> findAllByUserId(Long userId);

    List<ResponseEntity> findAllByListingId(Long listingId);

    @Query(value = "SELECT COUNT(listing_id) FROM responses WHERE listing_id = :listingId", nativeQuery = true)
    Long getTotalResponses(Long listingId);
}