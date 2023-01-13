package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.UserDTO;
import nl.fontys.s3.studenthousing.core.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.studenthousing.core.converters.UserConverter;
import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import nl.fontys.s3.studenthousing.domain.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class UserController {
    private final UserManager userManager;
    private final AccessToken accessToken;

    @PutMapping("register")
    public ResponseEntity<UserDTO> registerAccount(@RequestBody @Valid UserDTO dto){
        UserDTO user;
        try{
            user = UserConverter.convertToDTO(userManager.registerUser(UserConverter.convertToDomain(dto, UserRoles.STUDENT)));
        }
        catch(EmailAlreadyTakenException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(user);
    }

    @IsAuthenticated
    @GetMapping
    public ResponseEntity<UserDTO> getAccountSummary(){
        UserDTO user;
        try{
            user = UserConverter.convertToDTO(userManager.getUser(accessToken.getUserId()));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
