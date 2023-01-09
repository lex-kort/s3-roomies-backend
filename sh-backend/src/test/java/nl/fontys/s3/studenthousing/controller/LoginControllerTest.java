package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.interfaces.LoginManager;
import nl.fontys.s3.studenthousing.domain.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ListingController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes = LoginController.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginManager mockManager;

    @Test
    void login_shouldReturn200Response_onSuccessfulLogin() throws Exception {
        Login login = Login.builder()
                .email("generic@email.com")
                .password("abcde")
                .build();

        when(mockManager.authenticate(login)).thenReturn("It's a JWT token!");

        mockMvc.perform(post("/api/login")
                        .content(" { \"email\": \"generic@email.com\", \"password\": \"abcde\" } ")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().string("It's a JWT token!"));
        verify(mockManager).authenticate(login);
    }

    @Test
    void login_shouldReturn204Response_onInvalidCredentials() throws Exception {
        Login login = Login.builder()
                .email("generic@email.com")
                .password("abcde")
                .build();

        when(mockManager.authenticate(login)).thenThrow(InvalidCredentialsException.class);

        mockMvc.perform(post("/api/login")
                        .content(" { \"email\": \"generic@email.com\", \"password\": \"abcde\" } ")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(result -> {
                    assertNull(result.getResponse().getContentType());
                    assertEquals(0, result.getResponse().getContentLength());
                    assertEquals("", result.getResponse().getContentAsString());
                });

        verify(mockManager).authenticate(login);
    }
}