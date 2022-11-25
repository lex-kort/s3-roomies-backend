package nl.fontys.s3.studenthousing.business;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User registerUser(User user){
        User found = userRepository.findByEmail(user.getEmail());
        if(found != null){
            throw new EmailAlreadyTakenException();
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.registerUser(user);
    }
}
