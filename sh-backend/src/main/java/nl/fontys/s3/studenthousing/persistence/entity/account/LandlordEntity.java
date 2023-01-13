package nl.fontys.s3.studenthousing.persistence.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<ListingEntity> listings;
}
