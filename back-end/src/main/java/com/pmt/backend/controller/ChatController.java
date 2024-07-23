package com.pmt.backend.controller;

import com.pmt.backend.model.Chat;
import com.pmt.backend.model.User;
import com.pmt.backend.request.CreateChatRequest;
import com.pmt.backend.service.ChatService;
import com.pmt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser,user2);
        return chat;
    }
    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String  jwt){
        User reqUser = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUsersChat(reqUser.getId());
        return chats;
    }
}
