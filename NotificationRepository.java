package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.accessingdatamysql.model.Notification;


/**
 * The Interface NotificationRepository.
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer>{
    
}
