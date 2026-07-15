package com.hritik.hackathon.First.Contact.Repository;

import com.hritik.hackathon.First.Contact.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findAllByOrderByTimestampDesc();
}