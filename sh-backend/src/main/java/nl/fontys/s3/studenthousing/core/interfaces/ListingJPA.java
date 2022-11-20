package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingJPA extends JpaRepository<ListingEntity, Long> {
    List<ListingEntity> findByIsActiveTrue();
}
