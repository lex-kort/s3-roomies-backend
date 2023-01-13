package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import nl.fontys.s3.studenthousing.domain.account.Landlord;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ListingController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccessToken accessToken;

    @MockBean
    private UserManager mockManager;

    @Test
    void registerAccount_withNewEmail() throws Exception {
        User user = Student.builder()
                .email("johndoe@outlook.com")
                .password("abcde")
                .name("John")
                .surname("Doe")
                .phoneNumber("0612345678")
                .userRole(UserRoles.STUDENT)
                .build();

        when(mockManager.registerUser(user)).thenReturn(user);

        mockMvc.perform(put("/api/accounts/register")
                        .content(" { \"email\": \"johndoe@outlook.com\", \"password\": \"abcde\", \"name\": \"John\", \"surname\": \"Doe\", \"phoneNumber\": \"0612345678\", \"userRole\": \"STUDENT\" } ")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                    { "email": "johndoe@outlook.com", "name": "John", "surname": "Doe", "phoneNumber": "0612345678", "userRole": "STUDENT" }
                                                    """));

        verify(mockManager).registerUser(user);
    }

    @Test
    void registerAccount_withExistingEmail() throws Exception {
        User user = Student.builder()
                .email("johndoe@outlook.com")
                .password("abcde")
                .name("John")
                .surname("Doe")
                .phoneNumber("0612345678")
                .userRole(UserRoles.STUDENT)
                .build();

        when(mockManager.registerUser(user)).thenThrow(EmailAlreadyTakenException.class);

        mockMvc.perform(put("/api/accounts/register")
                        .content(" { \"email\": \"johndoe@outlook.com\", \"password\": \"abcde\", \"name\": \"John\", \"surname\": \"Doe\", \"phoneNumber\": \"0612345678\", \"userRole\": \"STUDENT\" } ")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(result -> {
                    assertNull(result.getResponse().getContentType());
                    assertEquals(0, result.getResponse().getContentLength());
                    assertEquals("", result.getResponse().getContentAsString());
                });

        verify(mockManager).registerUser(user);
    }

    @Test
    @WithMockUser(username = "lexdekort@live.nl", roles = { "LANDLORD" })
    void getAccountSummary_validToken() throws Exception {
        Landlord landlord = Landlord.builder()
                .id(1L)
                .name("Lex")
                .address("Windmolenstraat 1")
                .city("Helmond")
                .email("lexdekort@live.nl")
                .userRole(UserRoles.LANDLORD)
                .build();

        when(mockManager.getUser(accessToken.getUserId())).thenReturn(landlord);

        mockMvc.perform(get("/api/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                    {"id":1, "name": "Lex", "address": "Windmolenstraat 1", "city":"Helmond", "email": "lexdekort@live.nl", "userRole": "LANDLORD"}
                                                    """));

        verify(mockManager).getUser(accessToken.getUserId());
    }

    @Test
    @WithMockUser(username = "lexdekort@live.nl", roles = { "LANDLORD" })
    void getAccountSummary_invalidID() throws Exception {
        when(mockManager.getUser(accessToken.getUserId())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/api/accounts"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertNull(result.getResponse().getContentType());
                    assertEquals(0, result.getResponse().getContentLength());
                    assertEquals("", result.getResponse().getContentAsString());
                });

        verify(mockManager).getUser(accessToken.getUserId());
    }
}