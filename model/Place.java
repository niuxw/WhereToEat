package com.example.accessingdatamysql.model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * The Class Place.
 */
@Entity
@SequenceGenerator(name="PLACE_SEQ", sequenceName="place_sequence")
public class Place {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="PLACE_SEQ")
    private int id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /** The lat. */
    private double lat;
    
    /** The lng. */
    private double lng;
    
    /** The name. */
    private String name;
    
    /** The place id. */
    private String place_id;
    
    /** The types. */
    //private ArrayList<String> types;
    private String types;
    
    /** The open now. */
    private String open_now;
    
    /** The price level. */
    private int price_level;
    
    /** The rating num. */
    private int rating_num;
    
    /** The overall rating. */
    private double overallRating;

    /** The rating. */
    private double rating;
    
    /** The cosine similarity. */
    private double cosine_similarity;

    /**
     * Gets the overall rating.
     *
     * @return the overall rating
     */
    public double getOverallRating() {
        return overallRating;
    }

    /**
     * Sets the overall rating.
     *
     * @param overallRating the new overall rating
     */
    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * Gets the cosine similarity.
     *
     * @return the cosine similarity
     */
    public double getCosine_similarity() {
        return cosine_similarity;
    }

    /**
     * Sets the cosine similarity.
     *
     * @param cosine_similarity the new cosine similarity
     */
    public void setCosine_similarity(double cosine_similarity) {
        this.cosine_similarity = cosine_similarity;
    }

    /**
     * Gets the open now.
     *
     * @return the open now
     */
    public String getOpen_now() {
        return open_now;
    }

    /**
     * Sets the open now.
     *
     * @param open_now the new open now
     */
    public void setOpen_now(String open_now) {
        this.open_now = open_now;
    }

    /**
     * Gets the rating num.
     *
     * @return the rating num
     */
    public int getRating_num() {
        return rating_num;
    }

    /**
     * Sets the rating num.
     *
     * @param rating_num the new rating num
     */
    public void setRating_num(int rating_num) {
        this.rating_num = rating_num;
    }
//    public ArrayList<String> getTypes() {
//        return types;
//    }
//
//    public void setTypes(ArrayList<String> types) {
//        this.types = types;
//    }

    /**
 * Gets the types.
 *
 * @return the types
 */
public String getTypes() {
        return types;
    }

    /**
     * Sets the types.
     *
     * @param types the new types
     */
    public void setTypes(String types) {
        this.types = types;
    }

    /**
     * Gets the lat.
     *
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets the lat.
     *
     * @param lat the new lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Gets the lng.
     *
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * Sets the lng.
     *
     * @param lng the new lng
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the place id.
     *
     * @return the place id
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     * Sets the place id.
     *
     * @param place_id the new place id
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    /**
     * Gets the rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the rating.
     *
     * @param rating the new rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Checks if is open now.
     *
     * @return true, if is open now
     */
    public boolean isOpen_now() {
        return (open_now.equals("true")) ? true : false;
    }

    /**
     * Sets the open now.
     *
     * @param open_now the new open now
     */
    public void setOpen_now(boolean open_now) {
        this.open_now = open_now ? "true" : "false";
    }

    /**
     * Gets the price level.
     *
     * @return the price level
     */
    public int getPrice_level() {
        return price_level;
    }

    /**
     * Sets the price level.
     *
     * @param price_level the new price level
     */
    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }
}
