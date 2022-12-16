package nl.fontys.s3.studenthousing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
