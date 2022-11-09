package nl.fontys.s3.studenthousing.common.interfaces;

import nl.fontys.s3.studenthousing.persistence.entity.ListingEntity;
import nl.fontys.s3.studenthousing.common.domain.Listing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
class ListingRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    ListingRepository repo;
    @Test
    void findByIsActiveTrue_allActive(){
        List<Listing> listings = listingRepository.findByIsActiveTrue();

        for(Listing l : listings){
            Assertions.assertTrue(l.getIsActive());
        }
    }
}