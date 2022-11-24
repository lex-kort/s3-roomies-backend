package nl.fontys.s3.studenthousing.persistence;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.interfaces.UserJPA;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJPA userJPA;
}
