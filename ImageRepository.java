package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Interface ImageRepository.
 */
@Repository

public interface ImageRepository extends CrudRepository<Image, Integer>{

    /**
     * Find all by username.
     *
     * @param username the username
     * @return the list
     */
    List<Image> findAllByUsername(String username);
    
    /**
     * Find all by placeid.
     *
     * @param placeid the placeid
     * @return the list
     */
    List<Image> findAllByPlaceid(String placeid);

}
