package nl.fontys.s3.studenthousing.business.strategies;

import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.interfaces.OrderStrategy;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.Response;
import nl.fontys.s3.studenthousing.domain.account.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueueStrategyTest {
    List<Response> input;
    List<Response> expectedOutput;
    static OrderStrategy strategy;

    @BeforeAll
    static void initializeLogic(){
        strategy = new QueueStrategy();
    }

    @BeforeEach
    void initializeInput(){
        Listing listing = Listing.builder()
                .id(1L)
                .build();

        Student studentOne = Student.builder()
                .id(1L)
                .userRole(UserRoles.STUDENT)
                .build();

        Student studentTwo = Student.builder()
                .id(2L)
                .userRole(UserRoles.STUDENT)
                .build();

        Student studentThree = Student.builder()
                .id(3L)
                .userRole(UserRoles.STUDENT)
                .build();

        Response responseOne = Response.builder()
                .id(1L)
                .user(studentOne)
                .listing(listing)
                .responseDate(new Date(122, Calendar.MAY, 12, 12, 0, 0))
                .build();

        Response responseTwo = Response.builder()
                .id(2L)
                .user(studentTwo)
                .listing(listing)
                .responseDate(new Date(122, Calendar.MAY, 14, 6, 45, 0))
                .build();

        Response responseThree = Response.builder()
                .id(3L)
                .user(studentThree)
                .listing(listing)
                .responseDate(new Date(122, Calendar.FEBRUARY, 27, 18, 30, 0))
                .build();

        input = List.of(responseOne, responseTwo, responseThree);
        expectedOutput = List.of(responseThree, responseOne, responseTwo);
    }

    @Test
    void determineResponseOrder_ofQueueStrategy() {
        List<Response> actualOutput = strategy.determineResponseOrder(input);
        assertIterableEquals(expectedOutput, actualOutput);
    }
}