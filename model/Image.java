package com.example.accessingdatamysql.model;

import javax.persistence.*;


/**
 * The Class Image.
 */
@Entity
@SequenceGenerator(name="IMAGE_SEQ", sequenceName="image_sequence")

public class Image {
    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="IMAGE_SEQ")
    private Integer id;

    /** The username. */
    private String username;
    
    /** The placeid. */
    private String placeid;
    
    /** The path. */
    private String path;
    
    /** The type. */
    private String type;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the placeid.
     *
     * @return the placeid
     */
    public String getPlaceid() {
        return placeid;
    }

    /**
     * Sets the placeid.
     *
     * @param placeid the new placeid
     */
    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
