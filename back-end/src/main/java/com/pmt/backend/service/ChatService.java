package com.pmt.backend.service;

import com.pmt.backend.model.Chat;
import com.pmt.backend.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}

