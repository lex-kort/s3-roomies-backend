package nl.fontys.s3.studenthousing.persistence;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.converters.UserConverter;
import nl.fontys.s3.studenthousing.core.interfaces.UserJPA;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.account.User;
import nl.fontys.s3.studenthousing.persistence.entity.account.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJPA userJPA;

    @Override
    public User registerUser(User user){
        return UserConverter.convertToDomain(userJPA.save(UserConverter.convertToEntity(user)));
    }

    @Override
    public User findByEmail(String email){
        UserEntity user = userJPA.findByEmail(email);
        if(user == null){
            return null;
        }
        return UserConverter.convertToDomain(user);
    }
}
