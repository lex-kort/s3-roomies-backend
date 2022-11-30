package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;
import nl.fontys.s3.studenthousing.core.converters.ListingConverter;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.domain.Listing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class ListingController {
    private final ListingManager listingManager;

    @GetMapping
    public ResponseEntity<List<ListingDTO>> getActiveListings(){
        List<Listing> listings = listingManager.getActiveListings();
        if(listings.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listings.stream().map(ListingConverter::convertToDTO).toList());
    }

    @PostMapping
    public ResponseEntity<List<ListingDTO>> getFilteredListings(@RequestParam(value = "minArea", required = false) Integer minArea,
                                                              @RequestParam(value = "maxRent", required = false) Double maxRent,
                                                              @RequestParam(value = "pets", required = false) Boolean petsAllowed,
                                                              @RequestParam(value = "neighborhood", required = false) String neighborhood){
        List<Listing> listings = listingManager.getFilteredListings(minArea, maxRent, petsAllowed, neighborhood);
        if(listings.isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(listings.stream().map(ListingConverter::convertToDTO).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id") long id){
        Listing listing;
        try{
            listing = listingManager.getListing(id);
        }
        catch(InvalidListingIDException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(listing);
    }
}
