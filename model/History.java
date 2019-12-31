package com.example.accessingdatamysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * The Class History.
 */
@Entity
@SequenceGenerator(name="HIST_SEQ", sequenceName="hist_sequence")
public class History {
	
	/** The id. */
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HIST_SEQ")
	private Integer id;

    /** The name. It stands for username at this point */
    private String name;
    
    /** The restaurant. Restaurant name */
    private String restaurant;

    /** The place id. Unique ID for restaurants*/
    private String place_id;

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

	/** The time. Auto-generated timestamp */
	@CreationTimestamp
	private Timestamp time;
    

    /**
     * Sets the time.
     *
     * @param time the new time
     */
    public void setTime(Timestamp time) {
		this.time = time;
	}
    
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * Gets the restaurant.
	 *
	 * @return the restaurant
	 */
	public String getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets the restaurant.
	 *
	 * @param restaurant the new restaurant
	 */
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

//	ChIJ4_2-ZFZ37ocRKt3XegcFzjo
//	ChIJeRDmKNdn7ocR0ilLT5nvjTQ
//	ChIJLwQZPFaF7ocR4cghttz7w5k
//	ChIJ8R3NApB67ocR3_rhokq6oVY
//	ChIJSZPpJlZw7ocRPDourgJF69s
//	ChIJ7bzs27N67ocR0EYnknv8IV8
//	ChIJGypp6Pdw7ocRjmjAm2DEVbo ChIJid7Ma-za7YcRLZ-1SB8Q2Oo ChIJn0IasKdw7ocR1_PRR8p_HhU ChIJIfDPwVR37ocRemAAwt6SmrI ChIJd4Lsx-d57ocR24YaR0yn5vM ChIJJx569SiF7ocReWLZ-Dt3bh8 ChIJu4a27sJw7ocRYFn3zQCWfXs ChIJIeoPAk2a7ocRK5EjPXf7Kw4 ChIJ34Horoad7ocR2hkx9Hx7YVs ChIJwxZy6I167ocR29tNALuAMaQ

}
