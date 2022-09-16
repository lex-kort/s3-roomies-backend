package nl.fontys.s3.studenthousing.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.studenthousing.business.converter.ListingConverter;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.responses.GetActiveListingsResponse;
import nl.fontys.s3.studenthousing.persistence.ListingRepository;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/listings")
public class ListingController {

    private ListingRepository repository;

    @GetMapping
    public ResponseEntity<GetActiveListingsResponse> getActiveListings(){
        List<ListingEntity> results = repository.getActiveListings(0);

        final GetActiveListingsResponse response = new GetActiveListingsResponse();
        response.setListings(results.stream()
                .map(ListingConverter::convert)
                .toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id") long id){
        Optional<Listing> listing = repository.getById(id).map(ListingConverter::convert);
        if(listing.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listing.get());
    }
}
