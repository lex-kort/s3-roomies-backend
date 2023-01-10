package nl.fontys.s3.studenthousing.business;

import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.interfaces.AccessTokenEncoder;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import nl.fontys.s3.studenthousing.domain.Login;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class LoginManagerImplTest {
    @Mock
    private UserRepository mockRepo;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private AccessTokenEncoder mockTokenEncoder;
    @InjectMocks
    private LoginManagerImpl listingManager;

    private Login login;
    private User user;
    private AccessToken token;

    @BeforeEach
    void init(){
        login = Login.builder()
                .email("misterman@gmail.com")
                .password("googleman")
                .build();

        user = Student.builder()
                .email("misterman@gmail.com")
                .password("googleman")
                .userRole(UserRoles.STUDENT)
                .build();

        token = AccessToken.builder()
                .subject(user.getEmail())
                .roles(List.of(user.getUserRole().toString()))
                .userId(user.getId())
                .build();
    }

    @Test
    void authenticate_successfulLogin() {
        String expectedValue = "It's encoded";
        when(mockRepo.findByEmail(login.getEmail())).thenReturn(user);
        when(mockPasswordEncoder.matches(login.getPassword(), user.getPassword())).thenReturn(true);
        when(mockTokenEncoder.encode(token)).thenReturn(expectedValue);

        String actualValue = listingManager.authenticate(login);

        assertEquals(expectedValue, actualValue);
        verify(mockRepo).findByEmail(login.getEmail());
        verify(mockPasswordEncoder).matches(login.getPassword(), user.getPassword());
        verify(mockTokenEncoder).encode(token);
    }

    @Test
    void authenticate_userNotFound(){
        when(mockRepo.findByEmail(login.getEmail())).thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> {
            listingManager.authenticate(login);
        });
        verify(mockRepo).findByEmail(login.getEmail());
    }

    @Test
    void authenticate_passwordsDoNotMatch(){
        when(mockRepo.findByEmail(login.getEmail())).thenReturn(user);
        when(mockPasswordEncoder.matches(login.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> {
            listingManager.authenticate(login);
        });
        verify(mockRepo).findByEmail(login.getEmail());
        verify(mockPasswordEncoder).matches(login.getPassword(), user.getPassword());
    }
}