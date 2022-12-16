package nl.fontys.s3.studenthousing.business;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.interfaces.AccessTokenEncoder;
import nl.fontys.s3.studenthousing.core.interfaces.LoginManager;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import nl.fontys.s3.studenthousing.domain.Login;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginManagerImpl implements LoginManager {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public String authenticate(Login login) {
        User user = userRepository.findByEmail(login.getEmail());
        if(user == null){
            throw new InvalidCredentialsException();
        }
        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }
        return generateAccessToken(user);
    }


    private String generateAccessToken(User user) {
        List<String> roles = List.of(user.getUserRole().toString());
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userId(user.getId())
                        .build());
    }
}
