package com.example.accessingdatamysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 * The Class Preference.
 */
@Entity
@SequenceGenerator(name="PREF_SEQ", sequenceName="pref_sequence")
public class Preference {
	
	/** The id. */
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREF_SEQ")
	private Integer id;
	
	/** The username. */
	private String username;
	
	/** The Bakery. */
	private String Bakery;
	
	/** The Cafe. */
	private String Cafe;
	
	/** The Bar. */
	private String Bar;
	
	/** The American. */
	private String American;
	
	/** The Fastfood. */
	private String Fastfood;
	
	/** The Indian. */
	private String Indian;
	
	/** The Chinese. */
	private String Chinese;
	
	/** The Vietnamese. */
	private String Vietnamese;
	
	/** The Japanese. */
	private String Japanese;
	
	/** The Thai. */
	private String Thai;
	
	/** The Mexican. */
	private String Mexican;
	
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
	 * Gets the bakery.
	 *
	 * @return the bakery
	 */
	public String getBakery() {
		return Bakery;
	}
	
	/**
	 * Sets the bakery.
	 *
	 * @param bakery the new bakery
	 */
	public void setBakery(String bakery) {
		Bakery = bakery;
	}
	
	/**
	 * Gets the cafe.
	 *
	 * @return the cafe
	 */
	public String getCafe() {
		return Cafe;
	}
	
	/**
	 * Sets the cafe.
	 *
	 * @param cafe the new cafe
	 */
	public void setCafe(String cafe) {
		Cafe = cafe;
	}
	
	/**
	 * Gets the bar.
	 *
	 * @return the bar
	 */
	public String getBar() {
		return Bar;
	}
	
	/**
	 * Sets the bar.
	 *
	 * @param bar the new bar
	 */
	public void setBar(String bar) {
		Bar = bar;
	}
	
	/**
	 * Gets the american.
	 *
	 * @return the american
	 */
	public String getAmerican() {
		return American;
	}
	
	/**
	 * Sets the american.
	 *
	 * @param american the new american
	 */
	public void setAmerican(String american) {
		American = american;
	}
	
	/**
	 * Gets the fastfood.
	 *
	 * @return the fastfood
	 */
	public String getFastfood() {
		return Fastfood;
	}
	
	/**
	 * Sets the fastfood.
	 *
	 * @param fastfood the new fastfood
	 */
	public void setFastfood(String fastfood) {
		Fastfood = fastfood;
	}
	
	/**
	 * Gets the indian.
	 *
	 * @return the indian
	 */
	public String getIndian() {
		return Indian;
	}
	
	/**
	 * Sets the indian.
	 *
	 * @param indian the new indian
	 */
	public void setIndian(String indian) {
		Indian = indian;
	}
	
	/**
	 * Gets the chinese.
	 *
	 * @return the chinese
	 */
	public String getChinese() {
		return Chinese;
	}
	
	/**
	 * Sets the chinese.
	 *
	 * @param chinese the new chinese
	 */
	public void setChinese(String chinese) {
		Chinese = chinese;
	}
	
	/**
	 * Gets the vietnamese.
	 *
	 * @return the vietnamese
	 */
	public String getVietnamese() {
		return Vietnamese;
	}
	
	/**
	 * Sets the vietnamese.
	 *
	 * @param vietnamese the new vietnamese
	 */
	public void setVietnamese(String vietnamese) {
		Vietnamese = vietnamese;
	}
	
	/**
	 * Gets the japanese.
	 *
	 * @return the japanese
	 */
	public String getJapanese() {
		return Japanese;
	}
	
	/**
	 * Sets the japanese.
	 *
	 * @param japanese the new japanese
	 */
	public void setJapanese(String japanese) {
		Japanese = japanese;
	}
	
	/**
	 * Gets the thai.
	 *
	 * @return the thai
	 */
	public String getThai() {
		return Thai;
	}
	
	/**
	 * Sets the thai.
	 *
	 * @param thai the new thai
	 */
	public void setThai(String thai) {
		Thai = thai;
	}
	
	/**
	 * Gets the mexican.
	 *
	 * @return the mexican
	 */
	public String getMexican() {
		return Mexican;
	}
	
	/**
	 * Sets the mexican.
	 *
	 * @param mexican the new mexican
	 */
	public void setMexican(String mexican) {
		Mexican = mexican;
	}
	
	/**
	 * Gets the italian.
	 *
	 * @return the italian
	 */
	public String getItalian() {
		return Italian;
	}
	
	/**
	 * Sets the italian.
	 *
	 * @param italian the new italian
	 */
	public void setItalian(String italian) {
		Italian = italian;
	}
	
	/**
	 * Gets the greek.
	 *
	 * @return the greek
	 */
	public String getGreek() {
		return Greek;
	}
	
	/**
	 * Sets the greek.
	 *
	 * @param greek the new greek
	 */
	public void setGreek(String greek) {
		Greek = greek;
	}
	
	/**
	 * Gets the sandwich.
	 *
	 * @return the sandwich
	 */
	public String getSandwich() {
		return Sandwich;
	}
	
	/**
	 * Sets the sandwich.
	 *
	 * @param sandwich the new sandwich
	 */
	public void setSandwich(String sandwich) {
		Sandwich = sandwich;
	}
	
	/**
	 * Gets the breakfast.
	 *
	 * @return the breakfast
	 */
	public String getBreakfast() {
		return Breakfast;
	}
	
	/**
	 * Sets the breakfast.
	 *
	 * @param breakfast the new breakfast
	 */
	public void setBreakfast(String breakfast) {
		Breakfast = breakfast;
	}
	
	/**
	 * Gets the barbecue.
	 *
	 * @return the barbecue
	 */
	public String getBarbecue() {
		return Barbecue;
	}
	
	/**
	 * Sets the barbecue.
	 *
	 * @param barbecue the new barbecue
	 */
	public void setBarbecue(String barbecue) {
		Barbecue = barbecue;
	}
	
	/**
	 * Gets the pizza.
	 *
	 * @return the pizza
	 */
	public String getPizza() {
		return Pizza;
	}
	
	/**
	 * Sets the pizza.
	 *
	 * @param pizza the new pizza
	 */
	public void setPizza(String pizza) {
		Pizza = pizza;
	}
	
	/**
	 * Gets the steak.
	 *
	 * @return the steak
	 */
	public String getSteak() {
		return Steak;
	}
	
	/**
	 * Sets the steak.
	 *
	 * @param steak the new steak
	 */
	public void setSteak(String steak) {
		Steak = steak;
	}
	
	/** The Italian. */
	private String Italian;
	
	/** The Greek. */
	private String Greek;
	
	/** The Sandwich. */
	private String Sandwich;
	
	/** The Breakfast. */
	private String Breakfast;
	
	/** The Barbecue. */
	private String Barbecue;
	
	/** The Pizza. */
	private String Pizza;
	
	/** The Steak. */
	private String Steak;
}
