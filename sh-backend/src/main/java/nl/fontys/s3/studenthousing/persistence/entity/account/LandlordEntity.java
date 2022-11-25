package nl.fontys.s3.studenthousing.persistence.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("LANDLORD")
public class LandlordEntity extends UserEntity{
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "coc_number")
    private String cocNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "city")
    private String city;
}
