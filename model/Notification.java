package com.example.accessingdatamysql.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;


/**
 * The Class Notification.
 */
@Entity
@SequenceGenerator(name="NOTI_SEQ", sequenceName="noti_sequence")

public class Notification {
	
	/** The id. */
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTI_SEQ")
    private Integer id;
	
	/** The notification. */
	private String notification;
	
	/** The time. */
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
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * Sets the notification.
	 *
	 * @param notification the new notification
	 */
	public void setNotification(String notification) {
		this.notification = notification;
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
