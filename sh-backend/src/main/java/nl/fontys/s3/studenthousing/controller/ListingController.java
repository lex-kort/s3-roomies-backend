package nl.fontys.s3.studenthousing.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.business.manager.impl.ListingManagerImpl;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.request.GetActiveListingsRequest;
import nl.fontys.s3.studenthousing.domain.response.GetActiveListingsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/listings")
public class ListingController {

    private ListingManagerImpl listingManager;

    @GetMapping
    public ResponseEntity<GetActiveListingsResponse> getActiveListings(){
        GetActiveListingsRequest request = GetActiveListingsRequest.builder()
                .build();
        GetActiveListingsResponse response = listingManager.getActiveListings(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id") long id){
        Optional<Listing> listing = listingManager.getListing(id);
        if(listing.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        // this is a comment
        return ResponseEntity.ok().body(listing.get());
    }

}