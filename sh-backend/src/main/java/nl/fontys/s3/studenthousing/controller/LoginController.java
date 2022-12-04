package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.LoginDTO;
import nl.fontys.s3.studenthousing.core.exceptions.EmailNotFoundException;
import nl.fontys.s3.studenthousing.core.exceptions.InvalidCredentialsException;
import nl.fontys.s3.studenthousing.core.interfaces.LoginManager;
import nl.fontys.s3.studenthousing.domain.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {
    private final LoginManager loginManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO){
        String response;
        try{
            response = loginManager.authenticate(Login.builder().email(loginDTO.getEmail()).password(loginDTO.getPassword()).build());
        }
        catch(InvalidCredentialsException | EmailNotFoundException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
