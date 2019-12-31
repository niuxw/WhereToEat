package com.example.accessingdatamysql;

import com.example.accessingdatamysql.HistoryRepository;
import com.example.accessingdatamysql.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * The Class HistoryService.
 */
@Service
public class HistoryService {
    
    /** The history repository. */
    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Adds the new history.
     *
     * @param name the name
     * @param restaurant the restaurant
     */
    public void addNewHistory (String name, String restaurant) {
        History h = new History();
        h.setName(name);
        h.setRestaurant(restaurant);
        historyRepository.save(h);
    }

    /**
     * Gets the all history.
     *
     * @return the all history
     */
    public Iterable<History> getAllHistory() {
        return historyRepository.findAll();
    }
    
    /**
     * Delete history by name.
     *
     * @param name the name
     * @return the long
     */
    @Transactional
    public long deleteHisByName(String name){
        return historyRepository.deleteByName(name);
    }
    
    /**
     * Delete history by name and restaurant.
     *
     * @param name the name
     * @param restaurant the restaurant
     * @return the long
     */
    @Transactional
    public long deleteHisByNameAndRestaurant(String name, String restaurant){
        return historyRepository.deleteByNameAndRestaurant(name, restaurant);
    }

}
