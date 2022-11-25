package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.account.User;

public interface UserManager {
    User registerUser(User user);
}
