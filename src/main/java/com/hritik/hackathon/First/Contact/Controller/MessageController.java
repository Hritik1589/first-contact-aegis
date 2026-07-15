package com.hritik.hackathon.First.Contact.Controller;
import com.hritik.hackathon.First.Contact.Entity.Message;
import com.hritik.hackathon.First.Contact.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${aegis.node.id}")
    private String nodeId;

    // --- REAL-TIME LOCAL NETWORK CHAT ---
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message broadcastMessage(@Payload Message message) {
        message.setTimestamp(LocalDateTime.now());
        message.setOriginNodeId(nodeId);
        return messageRepository.save(message);
    }

    // --- INITIAL LOAD REST API ---
    @GetMapping("/api/messages")
    public List<Message> getLocalMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }

    // --- SNEAKERNET PROTOCOL (OFFLINE NODE SYNC) ---
    // Endpoint to download all messages to a USB drive
    @GetMapping("/api/sync/export")
    public List<Message> exportDataForRunner() {
        return messageRepository.findAll();
    }

    // Endpoint to upload messages from a runner's USB drive
    @PostMapping("/api/sync/import")
    public String importDataFromRunner(@RequestBody List<Message> importedMessages) {
        int newMessages = 0;
        for (Message msg : importedMessages) {
            // Only save if the UUID doesn't already exist in this local node
            if (!messageRepository.existsById(msg.getId())) {
                messageRepository.save(msg);
                newMessages++;
            }
        }
        return "Sync Complete. Merged " + newMessages + " new communications into local grid.";
    }
}