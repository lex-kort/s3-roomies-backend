package nl.fontys.s3.studenthousing.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public class Landlord extends User{
    private String companyName;
    private String cocNumber;
    private String address;
    private String zipCode;
    private String city;
}
