package nl.fontys.s3.studenthousing.core.converters;

import nl.fontys.s3.studenthousing.controller.dto.ResponseDTO;
import nl.fontys.s3.studenthousing.domain.Listing;
import nl.fontys.s3.studenthousing.domain.Response;
import nl.fontys.s3.studenthousing.domain.account.Student;
import nl.fontys.s3.studenthousing.persistence.entity.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseConverter {

    private ResponseConverter(){}

    public static ResponseDTO convertToDTO(Response response){
        return ResponseDTO.builder()
                .id(response.getId())
                .userId(response.getUser().getId())
                .listing(ListingConverter.convertToDTO(response.getListing()))
                .responseDate(response.getResponseDate().toLocalDate().toString())
                .position(response.getPosition())
                .hasAccepted(response.getHasAccepted())
                .build();
    }

    public static Response convertToDomain(ResponseDTO response){
        return Response.builder()
                .id(response.getId())
                .user(Student.builder().id(response.getUserId()).build())
                .listing(Listing.builder().id(response.getListing().getId()).build())
                .responseDate(LocalDateTime.parse(response.getResponseDate()))
                .position(response.getPosition())
                .hasAccepted(response.getHasAccepted())
                .build();
    }

    public static Response convertToDomain(ResponseEntity response){
        return Response.builder()
                .id(response.getId())
                .user(UserConverter.convertToDomain(response.getUser()))
                .listing(ListingConverter.convertToDomain(response.getListing()))
                .responseDate(response.getResponseDate())
                .position(response.getPosition())
                .hasAccepted(response.getHasAccepted())
                .build();
    }

    public static ResponseEntity convertToEntity(Response response){
        return ResponseEntity.builder()
                .id(response.getId())
                .user(UserConverter.convertToEntity(response.getUser()))
                .listing(ListingConverter.convertToEntity(response.getListing()))
                .responseDate(response.getResponseDate())
                .position(response.getPosition())
                .hasAccepted(response.getHasAccepted())
                .build();
    }
}
