package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface RestaurantDetailsRepository.
 */
@Repository

public interface RestaurantDetailsRepository extends CrudRepository<Place, Integer>{

    //Place findFirstByPlace_id(String place_id);
}
