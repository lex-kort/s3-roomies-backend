package nl.fontys.s3.studenthousing.domain.account;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Student extends User {
    private String studentNumber;
}
