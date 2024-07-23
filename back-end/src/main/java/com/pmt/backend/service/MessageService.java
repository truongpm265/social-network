package com.pmt.backend.service;

import com.pmt.backend.model.Chat;
import com.pmt.backend.model.Message;
import com.pmt.backend.model.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
