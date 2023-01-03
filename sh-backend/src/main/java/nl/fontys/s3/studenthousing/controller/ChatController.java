package nl.fontys.s3.studenthousing.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.studenthousing.controller.dto.ChatMessageDTO;
import nl.fontys.s3.studenthousing.core.configuration.security.isauthenticated.IsAuthenticated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<ChatMessageDTO> sendMessage(@RequestBody ChatMessageDTO chatMessageDTO){
        messagingTemplate.convertAndSend("/topic/publicmessages", chatMessageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
