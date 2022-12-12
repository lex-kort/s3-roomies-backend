package nl.fontys.s3.studenthousing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.fontys.s3.studenthousing.domain.account.User;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Long id;
    private User user;
    private Listing listing;
    private Date responseDate;
    private Integer position;
    private Boolean hasAccepted;
}
