package nl.fontys.s3.studenthousing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private Long senderId;
    private String sender;
    private String message;
}
