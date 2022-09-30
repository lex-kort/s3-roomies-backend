package nl.fontys.s3.studenthousing.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.business.manager.impl.ListingManagerImpl;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.request.GetActiveListingsRequest;
import nl.fontys.s3.studenthousing.domain.response.GetActiveListingsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ListingController {

    private ListingManagerImpl listingManager;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000/listings", allowedHeaders = "*")

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
