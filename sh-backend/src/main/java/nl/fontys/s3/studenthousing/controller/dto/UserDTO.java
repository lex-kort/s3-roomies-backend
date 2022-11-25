package nl.fontys.s3.studenthousing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private String password;
    @NotBlank
    private String name;
    private String prefix;
    @NotBlank
    private String surname;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String userRole;
    private String studentNumber;
    private String companyName;
    private String cocNumber;
    private String address;
    private String zipCode;
    private String city;
}
