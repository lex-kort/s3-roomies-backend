package nl.fontys.s3.studenthousing.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetActiveListingsRequest {
    private Integer offset;
    private String neighborhood;
    private Integer minSurfaceArea;
    private Double maxRent;
}
