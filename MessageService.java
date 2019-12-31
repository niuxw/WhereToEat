package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * The Class MessageService.
 */
@Service
public class MessageService {

    /** The message repository. */
    @Autowired
    private MessageRepository messageRepository;

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Send message.
     *
     * @param fusername the fusername
     * @param tusername the tusername
     * @param text the text
     * @param chatRoomID the chat room ID
     * @return the string
     */
    public String sendMessage (String fusername,
                        String tusername,
                        String text,
                               Integer chatRoomID) {

       Message m = new Message();
       m.setFusername(fusername);
       m.setTusername(tusername);
       m.setText(text);
       m.setChatRoomID(chatRoomID);
//       m.setSendtime(System.currentTimeMillis());
       messageRepository.save(m);
       return "Sent Message!";
    }

    /**
     * Gets the all message.
     *
     * @param tusername the tusername
     * @return the all message
     */
    public String getAllMessage(String tusername){
        List<Message> allMessages = messageRepository.findAllByTusername(tusername);
        List<Message> allMessages_ALL = messageRepository.findAllByTusername("ALL");
        List<Message> allMessages_SEND = messageRepository.findAllByFusername(tusername);

        String messages = "";
//        ListIterator<Message> it1 = allMessages.listIterator();
//        ListIterator<Message> it2 = allMessages_ALL.listIterator();
//        ListIterator<Message> it3 = allMessages_SEND.listIterator();

        List<Message> messageList1 = mergeSort(allMessages,allMessages_ALL);
        List<Message> messageList2 = mergeSort(messageList1,allMessages_SEND);

        for (int i =0; i < messageList2.size(); i++){
            Message cur = messageList2.get(i);
            messages += cur.getFusername()+ ": "+ cur.getText() + ";";
                    //+","+ cur.getSendtime()+";";
        }
        return messages;
    }

    /**
     * Merge sort.
     *
     * @param ml1 the ml 1
     * @param ml2 the ml 2
     * @return the list
     */
    public List<Message> mergeSort(List<Message> ml1, List<Message> ml2){
        if (ml1.size() == 0){
            return ml2;
        }
        if (ml2.size() == 0){
            return ml1;
        }

//        ListIterator<Message> it1 = ml1.listIterator();
//        ListIterator<Message> it2 = ml2.listIterator();
        int it1 = 0;
        int it2 = 0;

        List<Message> messages = new ArrayList<>();
//        if (m1.getSendtime().before(m2.getSendtime())){
//            messages.add(m1);
//            m1 = it1.next();
//        }
//        else {
//            messages.add(m2);
//            m2 = it2.next();
//        }
        while (it1<ml1.size() && it2 < ml2.size()){
            Message m1 = ml1.get(it1);
            Message m2 = ml2.get(it2);
            if (m1.getSendtime().before(m2.getSendtime())){
                messages.add(m1);
                it1++;
                //messages += m1.getFusername()+ ","+m1.getText()+","+m1.getSendtime()+";";
            }
            else {
                messages.add(m2);
                it2++;
                //messages += m2.getFusername() + ","+m2.getText()+","+m2.getSendtime()+";";
            }
        }
        while (it1<ml1.size()){
            messages.add(ml1.get(it1));
            it1++;
            //messages += m1.getFusername() + ","+m1.getText()+","+m1.getSendtime()+";";
        }
        while (it2<ml2.size()){
            messages.add(ml2.get(it2));
            it2++;
            //messages += m2.getFusername() + ","+m2.getText()+","+m2.getSendtime()+";";
        }
        return messages;
    }

}
