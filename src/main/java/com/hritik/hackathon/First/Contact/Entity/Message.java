package com.hritik.hackathon.First.Contact.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    private String id = UUID.randomUUID().toString();
    private String senderId;
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
    private boolean isEmergencyAlert;
    private String originNodeId;

    // Default constructor for JPA
    public Message() {}

}