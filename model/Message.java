package com.example.accessingdatamysql.model;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;


/**
 * The Class Message.
 */
@Entity
@SequenceGenerator(name="MESS_SEQ", sequenceName="mess_sequence")
public class Message {
    
    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="MESS_SEQ")

    private long id;
    
    /** The fusername. */
    private String fusername;
    
    /** The tusername. */
    private String tusername;
    
    /** The text. */
    private String text;
    
    /** The chat room ID. */
    private Integer chatRoomID;

    /** The sendtime. */
    @CreationTimestamp
	private Timestamp sendtime;

    /**
     * Gets the chat room ID.
     *
     * @return the chat room ID
     */
    public Integer getChatRoomID() {
        return chatRoomID;
    }

    /**
     * Sets the chat room ID.
     *
     * @param chatRoomID the new chat room ID
     */
    public void setChatRoomID(Integer chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    /**
     * Gets the sendtime.
     *
     * @return the sendtime
     */
    public Timestamp getSendtime() {
        return sendtime;
    }

    /**
     * Sets the sendtime.
     *
     * @param sendtime the new sendtime
     */
    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the fusername.
     *
     * @return the fusername
     */
    public String getFusername() {
        return fusername;
    }

    /**
     * Sets the fusername.
     *
     * @param fusername the new fusername
     */
    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    /**
     * Gets the tusername.
     *
     * @return the tusername
     */
    public String getTusername() {
        return tusername;
    }

    /**
     * Sets the tusername.
     *
     * @param tusername the new tusername
     */
    public void setTusername(String tusername) {
        this.tusername = tusername;
    }
}
