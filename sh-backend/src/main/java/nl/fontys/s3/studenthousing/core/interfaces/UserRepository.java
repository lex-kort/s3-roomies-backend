package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.account.User;

public interface UserRepository {
    User registerUser(User user);

    User findByEmail(String email);

    User findById(Long userId);
}
