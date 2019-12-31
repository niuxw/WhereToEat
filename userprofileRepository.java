package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.userprofile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * The Interface userprofileRepository.
 */
@Repository
public interface userprofileRepository extends CrudRepository<userprofile, Integer>{
    
    /**
     * Find first by name.
     *
     * @param name the name
     * @return the userprofile
     */
    userprofile findFirstByName(String name);
}
