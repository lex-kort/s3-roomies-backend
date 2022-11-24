package nl.fontys.s3.studenthousing.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.studenthousing.core.enums.UserRoles;

// EXPERIMENTAL ANNOTATION
// Utilize following structure in case of issues
// https://www.baeldung.com/lombok-builder-inheritance
@SuperBuilder
@Getter
public abstract class User {
    private Long id;
    private String name;
    private String prefix;
    private String surname;
    private String phonenumber;
    private String email;
    private String password;
    private UserRoles userRole;
}
