package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.ResponseDTO;
import nl.fontys.s3.studenthousing.controller.dto.UserDTO;
import nl.fontys.s3.studenthousing.core.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.studenthousing.core.converters.ResponseConverter;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidListingIDException;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseManager;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import nl.fontys.s3.studenthousing.domain.account.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseManager responseManager;
    private final AccessToken accessToken;

    @IsAuthenticated
    @RolesAllowed("ROLE_STUDENT")
    @PostMapping("{listingId}")
    public ResponseEntity<ResponseDTO> respondToListing(@PathVariable("listingId") Long listingId){
        ResponseDTO response;
        System.out.println(accessToken.getUserId());
        try{
            response = ResponseConverter.convertToDTO(responseManager.respondToListing(listingId, accessToken.getUserId()));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(InvalidListingIDException | InvalidCredentialsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_STUDENT")
    @DeleteMapping("{listingId}")
    public ResponseEntity<String> deleteResponse(@PathVariable("listingId") Long listingId){
        try{
            responseManager.deleteResponse(listingId, accessToken.getUserId());
        }
        catch(IllegalArgumentException | EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_LANDLORD")
    @GetMapping("{listingId}")
    public ResponseEntity<List<ResponseDTO>> getListingResponses(@PathVariable("listingId") Long listingId){
        List<ResponseDTO> responses;
        try{
            responses = responseManager.getListingResponses(listingId, accessToken.getUserId())
                    .stream()
                    .map(ResponseConverter::convertToDTO)
                    .toList();
        }
        catch(InvalidListingIDException e){
            return ResponseEntity.notFound().build();
        }
        catch(InvalidCredentialsException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responses);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_STUDENT")
    @GetMapping("user")
    public ResponseEntity<List<ResponseDTO>> getUserResponses(){
        return ResponseEntity.ok(responseManager.getUserResponses(accessToken.getUserId())
                .stream()
                .map(ResponseConverter::convertToDTO)
                .toList());
    }

    @GetMapping("total/{listingId}")
    public ResponseEntity<Long> getTotalResponses(@PathVariable("listingId") Long listingId){
        return ResponseEntity.ok(responseManager.getTotalResponses(listingId));
    }
}
