package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.chatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface chatRoomRepository.
 */
@Repository
public interface chatRoomRepository extends CrudRepository<chatRoom, Integer> {

}
