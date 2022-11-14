package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
class ListingJPATest {
    @Autowired
    private ListingJPA listingJPA;

    @Test
    void findByIsActiveTrue_allActive(){
        List<ListingEntity> listings = listingJPA.findByIsActiveTrue();

        for(ListingEntity l : listings){
            Assertions.assertTrue(l.getIsActive());
        }
    }
}