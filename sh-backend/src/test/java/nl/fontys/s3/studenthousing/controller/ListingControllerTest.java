package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.domain.Listing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ListingController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(classes = ListingController.class)
class ListingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ListingManager mockManager;

    @Test
    void getActiveListings_shouldReturn200Response_withAllActiveListings() throws Exception {
        List<Listing> response = List.of(
                Listing.builder().id(1L).address("Windmolenstraat 1").city("Eindhoven").rent(300.30).build(),
                Listing.builder().id(2L).address("Windmolenstraat 3").city("Eindhoven").rent(310.30).build()
        );

        when(mockManager.getActiveListings()).thenReturn(response);

        mockMvc.perform(get("/api/listings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                    [{"id":1, "address":"Windmolenstraat 1", "city":"Eindhoven", "rent":300.30}, {"id":2, "address":"Windmolenstraat 3", "city":"Eindhoven", "rent":310.30}]
                                                    """));
        verify(mockManager).getActiveListings();
    }

    @Test
    void getActiveListings_shouldReturn200Response_whenNoListingsPresent() throws Exception {
        when(mockManager.getActiveListings()).thenReturn(Collections.<Listing>emptyList());

        mockMvc.perform(get("/api/listings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNull(result.getResponse().getContentType());
                    assertEquals(0, result.getResponse().getContentLength());
                    assertEquals("", result.getResponse().getContentAsString());
                });

        verify(mockManager).getActiveListings();
    }

    @Test
    void getFilteredListings_shouldReturn200Response_withListingsFiltered_onRentAndSurfaceArea() throws Exception {
        List<Listing> response = List.of(
                Listing.builder().id(1L).address("Windmolenstraat 3").city("Eindhoven").rent(302.50).surfaceArea(20.5).petsAllowed(false).neighborhood("Strijp").build(),
                Listing.builder().id(2L).address("Langdonkstraat 6").city("Eindhoven").rent(305d).surfaceArea(20d).petsAllowed(true).neighborhood("Woensel").build()
        );
        Double minArea = 20d;
        Double maxRent = 305d;

        when(mockManager.getFilteredListings(minArea, maxRent, null, null)).thenReturn(response);

        mockMvc.perform(post("/api/listings")
                        .param("minArea", minArea.toString())
                        .param("maxRent", maxRent.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                    [{"id": 1, "address": "Windmolenstraat 3", "city": "Eindhoven", "rent": 302.50, "surfaceArea": 20.5, "petsAllowed": false, "neighborhood": "Strijp"}, 
                                                    {"id": 2, "address": "Langdonkstraat 6", "city": "Eindhoven", "rent": 305, "surfaceArea": 20, "petsAllowed": true, "neighborhood": "Woensel"}]
                                                    """));
        verify(mockManager).getFilteredListings(minArea, maxRent, null, null);
    }

    @Test
    void getFilteredListings_shouldReturn200Response_withNoListingsFound_onGivenRentAndSurfaceArea() throws Exception {
        Double minArea = 20d;
        Double maxRent = 305d;

        when(mockManager.getFilteredListings(minArea, maxRent, null, null)).thenReturn(Collections.<Listing>emptyList());

        mockMvc.perform(post("/api/listings")
                        .param("minArea", minArea.toString())
                        .param("maxRent", maxRent.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNull(result.getResponse().getContentType());
                    assertEquals(0, result.getResponse().getContentLength());
                    assertEquals("", result.getResponse().getContentAsString());
                });

        verify(mockManager).getFilteredListings(minArea, maxRent, null, null);
    }

    @Test
    void getListing_shouldReturn200Response_withListingDetails() throws Exception{
        Listing listing = Listing.builder().id(1L).address("Windmolenstraat 1").city("Eindhoven").rent(300.30).surfaceArea(19d).petsAllowed(true).neighborhood("Strijp").build();

        when(mockManager.getListing(1L)).thenReturn(listing);

        mockMvc.perform(get("/api/listings/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                    {"id": 1, "address": "Windmolenstraat 1", "city": "Eindhoven", "rent": 300.30, "surfaceArea": 19, "petsAllowed": true, "neighborhood": "Strijp"}
                                                    """));
        verify(mockManager).getListing(1L);
    }

    @Test
    void getListing_shouldReturn404Response_withUnknownID() throws Exception{
        when(mockManager.getListing(1L)).thenThrow(InvalidListingIDException.class);

        mockMvc.perform(get("/api/listings/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(
                        result -> {
                            assertNull(result.getResponse().getContentType());
                            assertEquals(0, result.getResponse().getContentLength());
                            assertEquals("", result.getResponse().getContentAsString());
                        }
                );
        verify(mockManager).getListing(1L);
    }
}