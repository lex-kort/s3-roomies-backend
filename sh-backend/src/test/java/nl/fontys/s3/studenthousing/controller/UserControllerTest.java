package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.core.converters.UserConverter;
import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import nl.fontys.s3.studenthousing.domain.account.Landlord;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}