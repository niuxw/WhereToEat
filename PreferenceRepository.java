package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.accessingdatamysql.model.Preference;


/**
 * The Interface PreferenceRepository.
 */
@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Integer>{
    
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the preference
	 */
	Preference findByUsername(String username);
}