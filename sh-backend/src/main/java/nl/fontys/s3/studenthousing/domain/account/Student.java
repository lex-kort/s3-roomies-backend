package nl.fontys.s3.studenthousing.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private String studentNumber;

    private Date signupDate;
}
