package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;
import nl.fontys.s3.studenthousing.core.converters.ListingConverter;
import nl.fontys.s3.studenthousing.core.interfaces.ListingManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ListingController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes = ListingController.class)
class ListingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ListingManager mockManager;

    @Test
    void getActiveListings_shouldReturn200ResponseWithAllActiveListings() throws Exception {
        List<ListingDTO> response = List.of(
                ListingDTO.builder().id(1L).address("Windmolenstraat 1").city("Eindhoven").rent(300.30).build(),
                ListingDTO.builder().id(2L).address("Windmolenstraat 3").city("Eindhoven").rent(310.30).build()
        );

        when(mockManager.getActiveListings()).thenReturn(response.stream().map(ListingConverter::convertToDomain).toList());

        mockMvc.perform(get("/api/listings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
//                .andExpect(content().json("""[{"id":1, "address":"Windmolenstraat 1", "city":"Eindhoven", "rent":300.30}, {"id":2, "address":"Windmolenstraat 3", "city":"Eindhoven", "rent":310.30}]"""));
        verify(mockManager).getActiveListings();
    }
}