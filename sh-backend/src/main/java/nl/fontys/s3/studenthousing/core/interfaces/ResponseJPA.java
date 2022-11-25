package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseJPA extends JpaRepository<ResponseEntity, Long>{
}
