package nl.fontys.s3.studenthousing.domain.account;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Landlord extends User{
    private String cocNumber;
    private String address;
    private String zipCode;
    private String city;
}
