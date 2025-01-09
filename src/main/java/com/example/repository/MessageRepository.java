package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Query("SELECT m FROM Message m WHERE postedBy=:postedBy")
    Optional<List<Message>> findMessageBypostedBy(@Param("postedBy") int postedBy);

    @Modifying
    @Query("UPDATE Message m SET m.messageText=:messageText WHERE m.messageId=:messageId")
    int updateMessage(@Param("messageId") int messageId, @Param("messageText") String messageText);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId=:messageId")
    int deleteById(@Param("messageId") int messageId);
}
