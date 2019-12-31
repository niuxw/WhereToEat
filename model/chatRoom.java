package com.example.accessingdatamysql.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The Class chatRoom.
 */
@Entity
@SequenceGenerator(name="CHATROOM_SEQ", sequenceName="chatroom_sequence")
public class chatRoom {
    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="CHATROOM_SEQ")
    private Integer id;

    /** The list of all users' IDs in the chat room. */
    private String users;

    /** The time. Auto-generated timestamp */
    @CreationTimestamp
    private Timestamp time;

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
     * Gets the users.
     *
     * @return the users
     */
    public String getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users the new users
     */
    public void setUsers(String users) {
        this.users = users;
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
     * Sets the time.
     *
     * @param time the new time
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
