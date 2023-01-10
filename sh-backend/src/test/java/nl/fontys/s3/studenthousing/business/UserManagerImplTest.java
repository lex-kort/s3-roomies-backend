package nl.fontys.s3.studenthousing.business;

import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {
    @Mock
    private UserRepository mockRepo;
    @Mock
    private PasswordEncoder mockEncoder;
    @InjectMocks
    private UserManagerImpl userManager;

    private User user;

    @BeforeEach
    void init(){
        user = Student.builder().id(1L).email("lexdekort@live.nl").studentNumber("1234").build();
    }

    @Test
    void registerUser_registerAsStudent_withNewEmail() {
        when(mockRepo.registerUser(user)).thenReturn(user);
        when(mockRepo.findByEmail(user.getEmail())).thenReturn(null);
        when(mockEncoder.encode(user.getPassword())).thenReturn("encoded");

        User actual = userManager.registerUser(user);

        assertEquals(user, actual);
        verify(mockRepo).registerUser(user);
        verify(mockRepo).findByEmail(user.getEmail());
    }

    @Test
    void registerUser_registerAsStudent_withExistingEmail() {
        User existingUser = Student.builder().email("lexdekort@live.nl").studentNumber("5678").build();
        when(mockRepo.findByEmail(user.getEmail())).thenReturn(existingUser);

        assertThrows(EmailAlreadyTakenException.class, () -> {
            userManager.registerUser(user);
        });
        verify(mockRepo).findByEmail(user.getEmail());
    }

    @Test
    void getUser_withValidID(){
        when(mockRepo.findById(1L)).thenReturn(user);

        User actualUser = userManager.getUser(1L);

        assertEquals(user, actualUser);
        verify(mockRepo).findById(1L);
    }

    @Test
    void getUser_withInvalidID(){
        when(mockRepo.findById(user.getId())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userManager.getUser(user.getId());
        });
        verify(mockRepo).findById(user.getId());
    }
}