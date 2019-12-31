package com.example.accessingdatamysql;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.example.accessingdatamysql.model.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;

import static com.example.accessingdatamysql.ImageService.encodeImage;
import static com.example.accessingdatamysql.PlacesService.details;
import static com.example.accessingdatamysql.PlacesService.nearbySearch;

/**
 * The Class MainController.
 */
@Controller
@RequestMapping(path="/demo")

public class MainController {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The history repository. */
	@Autowired
	private HistoryRepository historyRepository;

	/** The history service. */
	@Autowired
	private HistoryService historyService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The message service. */
	@Autowired
	private MessageService messageService;

	/** The restaurant details service. */
	@Autowired
	private RestaurantDetailsService restaurantDetailsService;

	/** The userprofile service. */
	@Autowired
	private userprofileService userprofileService;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private PreferenceRepository preferenceRepository;

	@Autowired
	private ImageService imageService;

	/**
	 * Adds the new user. Add new user by username and password and name. Return Saved if saved.
	 *
	 * @param name the name
	 * @param password the password
	 * @param username the username
	 * @return the string
	 */
	@PostMapping(path="/user")
	public @ResponseBody String addNewUser (@RequestParam String name
			,@RequestParam String password, @RequestParam String username) {

		if ((userRepository.getUserByName(name) != null) || name.equals("ALL")){
			return "Error, this name has been used!";
		}
		User n = new User();
		n.setName(name);
		n.setUsername(username);
		n.setPwd(password);

		userRepository.save(n);
		userprofile newUserprofile = new userprofile();
		newUserprofile.setName(username);
		newUserprofile.setAvg_distance(0.0);
		newUserprofile.setAvg_price(3.0);
		newUserprofile.setOccupation("student");
		newUserprofile.setTotal_history(0);
		newUserprofile.setTypes("test:0");
		userprofileService.updateUserprofile(newUserprofile);
		return "Saved";
	}

	/**
	 * Gets the all users. Get all users info from the database. Return all users information from the database.
	 *
	 * @return all users
	 */
	@GetMapping(path="/user")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Check user. Check if the username and password matches. Return 1 if matching, 0 if not matching.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the string
	 */
	@GetMapping(path="checkUser")
	public @ResponseBody String checkUser(@RequestParam String username,
			@RequestParam String password){
		User u = userRepository.findAllByUsername(username);
		return (u.getPwd().equals(password)) ? "1" : "0";
	}

	/**
	 * Adds the new history. Add a history entry for a user by the param name. And return saved if saved.
	 *
	 * @param name the name
	 * @param restaurant the restaurant
	 * @return the string
	 */
	@PostMapping(path="/history")
	public @ResponseBody String addNewHistory (@RequestParam String name
			,@RequestParam String restaurant) {

		History h = new History();
		h.setName(name);
		h.setRestaurant(restaurant);
		historyRepository.save(h);
		return "Saved";
	}

	//name is username
	@PostMapping(path="/history2")
	public @ResponseBody String addNewHistory2 (@RequestParam String username
			,@RequestParam String place_id) {

		//if (restaurantDetailsService.getRestaurant(place_id) == null){

		Place result = details(place_id);
			//restaurantDetailsService.addNewDetails(result);
		//}
		//update userProfile table
		Place curRestaurant = result; //restaurantDetailsService.getRestaurant(place_id);
		userprofileService.updateUserprofile(username, curRestaurant);

		History h = new History();
		h.setName(username);
		h.setPlace_id(place_id);
		h.setRestaurant(curRestaurant.getName().replaceAll("\\s",""));
		historyRepository.save(h);
		return "Saved";
	}

	/**
	 * Gets the all history. Get all history for all users from the database. It will return all history records.
	 *
	 * @param name the name
	 * @return the all history
	 */
	@GetMapping(path="/history")
	public @ResponseBody String getAllHistory(@RequestParam String name) {
		List<History> result = historyRepository.findAllByName(name);
		String[][] table = new String[result.size()][2];
		for (int i = 0;i<result.size();i++) {
			table[i][0] = result.get(i).getTime().toString();
			table[i][1] = result.get(i).getRestaurant().toString();
		}
		String tidy = "";
		for (int i = result.size()-1;i>=0;i--) {
			tidy += "Time: "+table[i][0];
			tidy += System.lineSeparator();
			tidy += "Restaurant: "+table[i][1];
			tidy += System.lineSeparator();
			tidy += "----------";
			tidy += System.lineSeparator();
		}
		return tidy;
	}


	/**
	 * Delete all history by name. Delete all history by the param name from the database.
	 *
	 * @param name the name
	 * @return the string
	 */
	@PostMapping(path="/historyDeleteAll")
	public @ResponseBody String deleteAllHistoryByName (@RequestParam String name) {
		long deleteNum = historyService.deleteHisByName(name);
		String a = Long.toString(deleteNum);
		return a +" have deleted!";
	}

	/**
	 * Delete all history by name and restaurant.
	 *
	 * @param name the name
	 * @param restaurant the restaurant
	 * @return the string
	 */
	@PostMapping(path="/historyDelete")
	public @ResponseBody String deleteAllHistoryByNameAndRestaurant (
			@RequestParam String name,
			@RequestParam String restaurant) {
		long deleteNum = historyService.deleteHisByNameAndRestaurant(name,restaurant);
		String a = Long.toString(deleteNum);
		return a +" have deleted!";
	}


	/**
	 * Send message. Send message by sender and receiver and the message needed to be sent.
	 *
	 * @param fusername the from name
	 * @param tusername the to name
	 * @param text the text
	 * @return the string
	 */
	@PostMapping(path="/message")
	public @ResponseBody String sendMessage (
			@RequestParam String fusername,
			@RequestParam String tusername,
			@RequestParam String text,
			@RequestParam Integer chatRoomID) {

		return messageService.sendMessage(fusername,tusername,text,chatRoomID);
	}

	@GetMapping(path = "/getAllMessage")
	public @ResponseBody String getAllMessage(@RequestParam String username){
		return messageService.getAllMessage(username);
	}

	/**
	 * Recommender. Recommend function
	 *
	 * @param name the username
	 * @param lat the latitude
	 * @param lng the longitude
	 * @param radius the radius
	 * @return the string
	 */
	@GetMapping(path="/recomm")
	public @ResponseBody String recommender (
			@RequestParam String name,
			@RequestParam String lat,
			@RequestParam String lng,
			@RequestParam int radius) {
		//PlacesService places = new PlacesService();
		double lat_d = Double.valueOf(lat);
		double lng_d = Double.valueOf(lng);

		int radius_meter = 1609*radius;  // convert from mile to meter
		userprofile userprofile = userprofileService.getUserprofileByName(name);
		recommender recommender = new recommender(userprofile,lat_d,lng_d,radius_meter);
		return recommender.getR();

//		ArrayList<Place> results = nearbySearch(lat_d,lng_d,radius_meter);
//		TreeMap<Double, String> map = new TreeMap<>(Collections.reverseOrder()); // rating, place_id
//		userprofile userprofile = userprofileService.getUserprofileByName(name);
//		double limitedPriceLevel = userprofile.getAvg_price()+1;
//
//		// check the restaurant price level if larger than the user avg_priceLevel +1
//		// if not then put this retaurant into the map in descending order.
//		for (int i = 0; i < results.size(); ++i){
//			Place place = results.get(i);
//			if (place.getPrice_level()<=limitedPriceLevel){
//				map.put(place.getRating(),place.getPlace_id());
//			}
//		}
//
//		String recommandRestaurants = "";
//		Set set = map.entrySet();
//		Iterator i = set.iterator();
//
//		// Traverse map and print elements
//		while (i.hasNext()) {
//			Map.Entry me = (Map.Entry)i.next();
//			recommandRestaurants += me.getValue() + " ";
//		}
//		return recommandRestaurants;
	}

	/**
	 * Update profile.
	 *
	 * @param name the name
	 * @param occupation the occupation
	 * @param types the types
	 * @return the string
	 */
	@PostMapping(path="/profile")
	public @ResponseBody String updateProfile (
			@RequestParam String name,
			@RequestParam String occupation,
			@RequestParam String types) {

		userprofile newUserprofile = userprofileService.getUserprofileByName(name);
		newUserprofile.setOccupation(occupation);
		newUserprofile.setTypes(types);
		userprofileService.updateUserprofile(newUserprofile);
		return "update profile";
	}

	/**
	 * Gets the profile.
	 *
	 * @param name the name
	 * @return the profile
	 */
	@GetMapping (path="/getProfile")
	public @ResponseBody userprofile getProfile (
			@RequestParam String name) {

		userprofile newUserprofile = userprofileService.getUserprofileByName(name);
		return newUserprofile;
	}

	@GetMapping (path="/getAllNoti")
	public @ResponseBody List<String> getAllNoti (
			) {
		Iterable<Notification> result = notificationRepository.findAll();
		Iterator<Notification> iterator = result.iterator();
		List<String> value = new ArrayList<String>();
		while (iterator.hasNext()){
			Notification n = iterator.next();
            value.add(n.getTime()+"   "+n.getNotification());
        }
		return value;
	}

	@PostMapping(path="/postNoti")
	public @ResponseBody String postNoti (
			@RequestParam String notification
			) {
		Notification noti = new Notification();
		noti.setNotification(notification);
		notificationRepository.save(noti);
		return "saved";
	}

	@GetMapping (path="/getLatest")
	public @ResponseBody String getLatest (
			) {

		Iterable<Notification> result = notificationRepository.findAll();
		Iterator<Notification> iterator = result.iterator();
		Notification value;
		do {
            value = iterator.next();
        } while (iterator.hasNext());
	    return value.getNotification();
	}


	@GetMapping (path="/preferences")
	public @ResponseBody Preference getPreferences (
			@RequestParam String username
			) {

		return preferenceRepository.findByUsername(username);
	}

	@PostMapping (path="/preferences")
	public @ResponseBody String postPreferences (
			@RequestParam String username,
			@RequestParam String Bakery,
			@RequestParam String Cafe,
			@RequestParam String Bar,
			@RequestParam String American,
			@RequestParam String Fastfood,
			@RequestParam String Indian,
			@RequestParam String Chinese,
			@RequestParam String Vietnamese,
			@RequestParam String Japanese,
			@RequestParam String Thai,
			@RequestParam String Mexican,
			@RequestParam String Italian,
			@RequestParam String Greek,
			@RequestParam String Sandwich,
			@RequestParam String Breakfast,
			@RequestParam String Barbecue,
			@RequestParam String Pizza,
			@RequestParam String Steak
			) {
		Preference pref = new Preference();
		pref.setUsername(username);

		pref.setAmerican(American);
		pref.setBakery(Bakery);
		pref.setBar(Bar);
		pref.setBarbecue(Barbecue);
		pref.setBreakfast(Breakfast);
		pref.setCafe(Cafe);
		pref.setChinese(Chinese);
		pref.setFastfood(Fastfood);
		pref.setGreek(Greek);
		pref.setIndian(Indian);
		pref.setItalian(Italian);
		pref.setJapanese(Japanese);
		pref.setMexican(Mexican);
		pref.setPizza(Pizza);
		pref.setSandwich(Sandwich);
		pref.setSteak(Steak);
		pref.setThai(Thai);
		pref.setVietnamese(Vietnamese);


		preferenceRepository.save(pref);

		return "saved";
	}

	@PostMapping (path="/img")
	public @ResponseBody String uploadImg(@RequestParam String username,
							@RequestParam String placeid,
							@RequestParam("editormd-image-file") MultipartFile multipartFile)  {
		if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
			return "Error: empty file";
		}
		String contentType = multipartFile.getContentType();
		if (!contentType.contains("")) {
			return "Error: wrong image type";
		}

		String filePath = "WebImg" + File.separator + username;
		String total_path = null;
		try {
			total_path = imageService.saveImg(multipartFile, filePath);
			if(StringUtils.isNotBlank(total_path)){
				imageService.saveToDB(username,placeid,total_path);
				return "upload an image!";
			}
		} catch (IOException e) {
			return "Error: save image error";
		}

		return "I do not know why reach here...";
	}

	//https://stackoverflow.com/questions/44839753/returning-json-object-as-response-in-spring-boot
//	@GetMapping (path="/getImgByUser")
//	public ResponseEntity<Object> getImgByUser(@RequestParam String username) throws IOException, JSONException {
//		Iterable<Image> result = imageService.getImgsByUsername(username);
//		Iterator<Image> iterator = result.iterator();
//		HashMap<String, String> map = new HashMap<>(); // type
//		ArrayList<String[]> r = new ArrayList<>();
//		while (iterator.hasNext()){
//			Image img = iterator.next();
//			String[] val = new String[]{img.getType(), encodeImage(img.getPath())};
//			r.add(val);
//		}
//		return r;

//		List<JSONObject> entities = new ArrayList<JSONObject>();
//		while (iterator.hasNext()){
//			JSONObject entity = new JSONObject();
//			entity.put("aa", "bb");
//			entities.add(entity);
//		}
//	}

	@GetMapping (path="/getImgByPlace")
	public ArrayList<String[]> getImgByPlace(@RequestParam String placeid) throws IOException {
		Iterable<Image> result = imageService.getImgsByPlaceid(placeid);
		Iterator<Image> iterator = result.iterator();
		HashMap<String, String> map = new HashMap<>(); // type
		ArrayList<String[]> r = new ArrayList<>();
		while (iterator.hasNext()){
			Image img = iterator.next();
			String[] val = new String[]{img.getType(), encodeImage(img.getPath())};
			r.add(val);
		}
		return r;
	}
}