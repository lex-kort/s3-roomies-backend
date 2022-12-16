package nl.fontys.s3.studenthousing.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Login {
    private String email;
    private String password;
}
