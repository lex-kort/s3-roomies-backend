package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.account.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
