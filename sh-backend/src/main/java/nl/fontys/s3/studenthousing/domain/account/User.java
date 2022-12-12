package nl.fontys.s3.studenthousing.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.studenthousing.core.enums.UserRoles;

// EXPERIMENTAL LOMBOK ANNOTATION
// Utilize following structure in case of issues
// https://www.baeldung.com/lombok-builder-inheritance
@SuperBuilder
@Getter
public abstract class User {
    private Long id;
    private String name;
    private String prefix;
    private String surname;
    private String phoneNumber;
    private String email;
    @Setter
    private String password;
    private UserRoles userRole;
//    private String userRole;
}
