package com.example.accessingdatamysql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.accessingdatamysql.model.History;
import org.springframework.stereotype.Repository;


/**
 * The Interface HistoryRepository.
 */
@Repository
public interface HistoryRepository extends CrudRepository<History, Integer>{

	/**
	 * Delete by name.
	 *
	 * @param name the name
	 * @return the long
	 */
	long deleteByName(String name);
	
	/**
	 * Delete by name and restaurant.
	 *
	 * @param name the name
	 * @param restaurant the restaurant
	 * @return the long
	 */
	long deleteByNameAndRestaurant(String name, String restaurant);
	
	/**
	 * Find all by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<History> findAllByName(String name);

}
