package com.example.accessingdatamysql;
import com.example.accessingdatamysql.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The Class RestaurantDetailsService.
 */
@Service
public class RestaurantDetailsService {

    /** The restaurant details repository. */
    @Autowired
    private RestaurantDetailsRepository restaurantDetailsRepository;

    /**
     * Adds the new details.
     *
     * @param place the place
     */
    public void addNewDetails (Place place) {
        restaurantDetailsRepository.save(place);
    }

//    public Place getRestaurant(String place_id){
//        return restaurantDetailsRepository.findFirstByPlace_id(place_id);
//    }
}
