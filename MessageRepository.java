package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    /**
     * Find all by name.
     *
     * @param tusername the username of the receiver
     * @return the list
     */
    List<Message> findAllByTusername(String tusername);

    /**
     * Find all by name.
     *
     * @param fusername the username of the sender
     * @return the list
     */
    List<Message> findAllByFusername(String fusername);
}
