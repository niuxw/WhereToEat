package com.example.accessingdatamysql.model;

import javax.persistence.*;


/**
 * The Class userprofile.
 */
@Entity
@SequenceGenerator(name="USERP_SEQ", sequenceName="userp_sequence")

public class userprofile {
    
    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="USERP_SEQ")
    private Integer id;

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

    /** The name. */
    private String name;
    
    /** The occupation. */
    private String occupation;
    
    /** The total history. */
    private int total_history;
    
    /** The avg price. */
    private double avg_price;
    
    /** The avg distance. */
    private double avg_distance;
    
    /** The types. */
    private String types;

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
     * Gets the occupation.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the occupation.
     *
     * @param occupation the new occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Gets the avg distance.
     *
     * @return the avg distance
     */
    public double getAvg_distance() {
        return avg_distance;
    }

    /**
     * Sets the avg distance.
     *
     * @param avg_distance the new avg distance
     */
    public void setAvg_distance(double avg_distance) {
        this.avg_distance = avg_distance;
    }

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
     * Gets the total history.
     *
     * @return the total history
     */
    public int getTotal_history() {
        return total_history;
    }

    /**
     * Sets the total history.
     *
     * @param total_history the new total history
     */
    public void setTotal_history(int total_history) {
        this.total_history = total_history;
    }

    /**
     * Gets the avg price.
     *
     * @return the avg price
     */
    public double getAvg_price() {
        return avg_price;
    }

    /**
     * Sets the avg price.
     *
     * @param avg_price the new avg price
     */
    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }
}
