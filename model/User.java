package com.example.accessingdatamysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * The Class User.
 */
@Entity
@SequenceGenerator(name="USER_SEQ", sequenceName="user_sequence")
public class User {

	 /** The id. */
 	@Id
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
	    private Integer id;

	    /** The name. */
    	private String name;

	    /** The username. */
    	private String username;

	    /** The pwd. */
    	private String pwd;
	    
		/**
		 * Gets the pwd.
		 *
		 * @return the pwd
		 */
		public String getPwd() {
			return pwd;
		}

		/**
		 * Sets the pwd.
		 *
		 * @param pwd the new pwd
		 */
		public void setPwd(String pwd) {
			this.pwd = pwd;
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


}
