package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.core.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.studenthousing.core.interfaces.ResponseManager;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/response")
@CrossOrigin("https://localhost:3000")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseManager responseManager;
    private final AccessToken accessToken;

    @IsAuthenticated
    @PostMapping("{id}")
    private ResponseEntity respondToListing(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }
}
