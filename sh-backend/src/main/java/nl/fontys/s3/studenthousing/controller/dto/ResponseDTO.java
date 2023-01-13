package nl.fontys.s3.studenthousing.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Long id;
    @NotBlank
    private Long userId;
    @NotBlank
    private ListingDTO listing;
    private String responseDate;
    private Long position;
    private Boolean hasAccepted;
}
