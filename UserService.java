package com.example.accessingdatamysql;

import com.example.accessingdatamysql.UserRepository;
import com.example.accessingdatamysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class UserService.
 */
@Service
public class UserService {
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Gets the id by name.
     *
     * @param name the name
     * @return the id by name
     */
    public int getIdByName(String name){
        User user = userRepository.getUserByName(name);
        return user.getId();
    }
}
