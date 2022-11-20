package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<UserEntity, Long> {
}
