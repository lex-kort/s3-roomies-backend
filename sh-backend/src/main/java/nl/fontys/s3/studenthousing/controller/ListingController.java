package nl.fontys.s3.studenthousing.controller;

import nl.fontys.s3.studenthousing.common.domain.Listing;
import nl.fontys.s3.studenthousing.common.interfaces.ListingManager;
import nl.fontys.s3.studenthousing.controller.converter.ListingConverter;
import nl.fontys.s3.studenthousing.controller.dto.ListingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin("http://localhost:3000")
public class ListingController {
    private ListingManager listingManager;

    public ListingController(ListingManager listingManager){
        this.listingManager = listingManager;
    }

    @GetMapping
    public ResponseEntity<List<ListingDTO>> getActiveListings(@RequestParam(value = "minArea", required = false) Integer minArea,
                                                              @RequestParam(value = "maxRent", required = false) Double maxRent,
                                                              @RequestParam(value = "pets", required = false) Boolean petsAllowed,
                                                              @RequestParam(value = "neighborhood", required = false) String neighborhood){
        List<Listing> listings;
        if(minArea != null || maxRent != null || petsAllowed != null || neighborhood != null) {
            listings = listingManager.getFilteredListings(minArea, maxRent, petsAllowed, neighborhood);
        }
        else{
            listings = listingManager.getActiveListings();
        }

        if(listings.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listings.stream().map(ListingConverter::convertToDTO).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id") long id){
        Listing listing = listingManager.getListing(id);
        return ResponseEntity.ok().body(listing);
    }
}
