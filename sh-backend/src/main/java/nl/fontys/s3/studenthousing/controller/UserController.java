package nl.fontys.s3.studenthousing.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.UserDTO;
import nl.fontys.s3.studenthousing.core.converters.UserConverter;
import nl.fontys.s3.studenthousing.core.exceptions.EmailAlreadyTakenException;
import nl.fontys.s3.studenthousing.core.interfaces.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
//@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class UserController {
    private final UserManager userManager;

    @PutMapping("register")
    public ResponseEntity registerAccount(@RequestBody UserDTO dto){
        UserDTO user;
        try{
            user = UserConverter.convertToDTO(userManager.registerUser(UserConverter.convertToDomain(dto)));
        }
        catch(EmailAlreadyTakenException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already taken");
        }
        return ResponseEntity.ok(user);
    }
}
