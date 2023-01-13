package nl.fontys.s3.studenthousing.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageDTO {
    private Long senderId;
    private String sender;
    private String message;
}
