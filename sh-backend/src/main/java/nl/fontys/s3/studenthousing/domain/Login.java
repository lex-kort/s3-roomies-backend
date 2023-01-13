package nl.fontys.s3.studenthousing.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Login {
    private String email;
    private String password;
}
