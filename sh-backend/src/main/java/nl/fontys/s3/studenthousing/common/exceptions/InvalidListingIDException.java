package nl.fontys.s3.studenthousing.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidListingIDException extends ResponseStatusException {
    public InvalidListingIDException() {
        super(HttpStatus.NOT_FOUND, "INVALID_LISTING_ID");
    }
}
