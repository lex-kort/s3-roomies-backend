package nl.fontys.s3.studenthousing.business.impl;

import nl.fontys.s3.studenthousing.business.UserManagerImpl;
import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserRepository;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.domain.account.User;
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

    @Test
    void registerUser_registerAsStudent_withNewEmail() {
        User expected = Student.builder().email("lexdekort@live.nl").studentNumber("1234").build();
        when(mockRepo.registerUser(expected)).thenReturn(expected);
        when(mockRepo.findByEmail(expected.getEmail())).thenReturn(null);
        when(mockEncoder.encode(expected.getPassword())).thenReturn("encoded");

        User actual = userManager.registerUser(expected);

        assertEquals(expected, actual);
        verify(mockRepo).registerUser(expected);
        verify(mockRepo).findByEmail(expected.getEmail());
    }

    @Test
    void registerUser_registerAsStudent_withExistingEmail() {
        User newUser = Student.builder().email("lexdekort@live.nl").studentNumber("1234").build();
        User existingUser = Student.builder().email("lexdekort@live.nl").studentNumber("5678").build();
        when(mockRepo.findByEmail(newUser.getEmail())).thenReturn(existingUser);

        assertThrows(EmailAlreadyTakenException.class, () -> {
            userManager.registerUser(newUser);
        });

        verify(mockRepo).findByEmail(newUser.getEmail());
    }
}