package com.pmt.backend.controller;

import com.pmt.backend.model.Message;
import com.pmt.backend.model.User;
import com.pmt.backend.service.MessageService;
import com.pmt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/message/chat/{chatId}")
    public Message createMassage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(user,chatId,req);
        return message;
    }
    @GetMapping("/api/message/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }
}
