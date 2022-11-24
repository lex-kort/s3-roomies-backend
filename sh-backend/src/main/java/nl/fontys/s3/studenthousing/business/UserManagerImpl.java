package nl.fontys.s3.studenthousing.business;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
}
