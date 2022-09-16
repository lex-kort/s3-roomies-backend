package nl.fontys.s3.studenthousing.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.studenthousing.domain.Listing;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetActiveListingsResponse {
    private List<Listing> listings;
}
