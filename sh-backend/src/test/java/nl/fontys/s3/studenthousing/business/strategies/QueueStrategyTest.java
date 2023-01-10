package nl.fontys.s3.studenthousing.business.strategies;

import nl.fontys.s3.studenthousing.core.enums.UserRoles;
import nl.fontys.s3.studenthousing.core.exceptions.EmptyInputException;
import nl.fontys.s3.studenthousing.core.interfaces.OrderStrategy;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.Response;
import nl.fontys.s3.studenthousing.domain.account.Landlord;
import nl.fontys.s3.studenthousing.domain.account.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QueueStrategyTest {
    @InjectMocks
    private QueueStrategy strategy;

    @Test
    void determineResponseOrder_ofQueueStrategy_withAllStudents() {
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

        List<Response> input = List.of(responseOne, responseTwo, responseThree);
        List<Response> expectedOutput = List.of(responseThree, responseOne, responseTwo);

        List<Response> actualOutput = strategy.determineResponseOrder(input);

        assertIterableEquals(expectedOutput, actualOutput);
    }

    @Test
    void determineResponseOrder_ofQueueStrategy_notAllStudents() {
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

        Landlord landlord = Landlord.builder()
                .id(3L)
                .userRole(UserRoles.LANDLORD)
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
                .user(landlord)
                .listing(listing)
                .responseDate(new Date(122, Calendar.FEBRUARY, 27, 18, 30, 0))
                .build();

        List<Response> input = List.of(responseOne, responseTwo, responseThree);
        List<Response> expectedOutput = List.of(responseOne, responseTwo);

        List<Response> actualOutput = strategy.determineResponseOrder(input);

        assertIterableEquals(expectedOutput, actualOutput);
    }

    @Test
    void determineResponseOrder_ofQueueStrategy_withEmptyInput() {
        assertThrows(EmptyInputException.class, () -> {
            strategy.determineResponseOrder(null);
        });
    }
}