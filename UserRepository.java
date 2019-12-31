package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.model.User;
import org.springframework.stereotype.Repository;


/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    
    /**
     * Gets the user by name.
     *
     * @param name the name
     * @return the user by name
     */
    User getUserByName(String name);
    
    /**
     * Find all by username.
     *
     * @param username the username
     * @return the user
     */
    User findAllByUsername(String username);

}
