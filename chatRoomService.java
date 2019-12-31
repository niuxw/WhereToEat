package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.chatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class chatRoomService {
    /** The history repository. */
    @Autowired
    private chatRoomRepository chatRoomRepository;

    /**
     * Adds the new history.
     *
     * @param users the list of the users' IDs in the chat room
     */
    public int addNewChatRoom (ArrayList<Integer> users) {
        chatRoom chat = new chatRoom();
        chat.setUsers(users.toString());
        chatRoomRepository.save(chat);
        return chat.getId();
    }
}
