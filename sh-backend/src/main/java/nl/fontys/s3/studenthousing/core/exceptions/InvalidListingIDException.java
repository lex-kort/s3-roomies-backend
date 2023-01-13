package nl.fontys.s3.studenthousing.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidListingIDException extends ResponseStatusException {
    public InvalidListingIDException() {
        super(HttpStatus.NOT_FOUND, "INVALID_OBJECT_ID");
    }
}
