package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Place;
import com.example.accessingdatamysql.model.userprofile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static com.example.accessingdatamysql.PlacesService.nearbySearch;

/**
 * The Class recommender.
 */
public class recommender {
    
    /** The userprofile. */
    private userprofile userprofile;
    
    /** The lat. */
    private double lat;
    
    /** The lng. */
    private double lng;
    
    /** The radius. */
    private int radius;

    /**
     * Instantiates a new recommender.
     *
     * @param userprofile the userprofile
     * @param lat the lat
     * @param lng the lng
     * @param radius the radius
     */
    public recommender(userprofile userprofile,
                       double lat,
                       double lng,
                       int radius) {
        this.userprofile = userprofile;
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
    }

    /**
     * Gets the r.
     *
     * @return the r
     */
    public String getR() {
        ArrayList<Place> results = nearbySearch(lat,lng,radius);
        TreeMap<Double, String> map = new TreeMap<>(); // rating, place_id Collections.reverseOrder()
        double limitedPriceLevel = 3.0;
        HashMap<String, Integer> map1 = Str2Map(userprofile.getTypes());

        double th1,th2 = 0; // params for mod_rating and cosine_similarity
        if(userprofile.getOccupation().equals("student")){
            th2 = 0.618;
            th1 = 1 - th2;
            limitedPriceLevel = userprofile.getAvg_price() + 1;
        }
        else if(userprofile.getOccupation().equals("traveller")){
            th1 = 0.618;
            th2 = 1 - th1;
            limitedPriceLevel = userprofile.getAvg_price() + 1.5;
        }
        else {
            th2 = 0.5;
            th1 = 0.5;
            limitedPriceLevel = userprofile.getAvg_price() + 2;
        }

        // update cosine_similarity and overall rating for each result
        for (int i = 0; i < results.size(); ++i){
            Place place = results.get(i);
            HashMap<String, Integer> map2 = Type2Map(place.getTypes());
            if (map1.size() > map2.size()){
                place.setCosine_similarity(cosine_similarity(map1,map2));
            }
            else{
                place.setCosine_similarity(cosine_similarity(map2,map1));
            }
            place.setOverallRating(th1*place.getRating() + th2*place.getCosine_similarity());
        }

        // check the restaurant price level if larger than the user avg_priceLevel +1
        // if not then put this retaurant into the map in descending order.
        for (int i = 0; i < results.size(); ++i){
            Place place = results.get(i);
            if (place.getPrice_level()<=limitedPriceLevel){
                map.put(place.getOverallRating(),place.getPlace_id());
            }
        }

        String recommandRestaurants = "";
        Set set = map.entrySet();
        Iterator i = set.iterator();

        // Traverse map and print elements
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            recommandRestaurants += me.getValue() + " ";
        }
        return recommandRestaurants;
    }

    // map1.size > map2.size
    /**
     * Cosine similarity.
     *
     * @param map1 the map 1
     * @param map2 the map 2
     * @return the double
     */
    // the return value be closer to 1 -> two types are more similar
    private double cosine_similarity(HashMap<String, Integer> map1,
                                     HashMap<String, Integer> map2){

        double denominator = 0;
        int nominator = 0;
        for (Map.Entry mapElement : map1.entrySet()) {
            String k1 = (String)mapElement.getKey();
            int v1 = (int)mapElement.getValue();
            if (map2.containsKey(k1)){
                nominator += v1*map2.get(k1);
            }
        }
        int denominator1 = 0;
        int denominator2 = 0;
        for (Map.Entry mapElement : map1.entrySet()) {
            int v1 = (int)mapElement.getValue();
            denominator1 += v1*v1;
        }
        for (Map.Entry mapElement : map2.entrySet()) {
            int v2 = (int)mapElement.getValue();
            denominator2 += v2*v2;
        }
        denominator = Math.sqrt(denominator1) + Math.sqrt(denominator2);
        return nominator / denominator;
    }

    /**
     * Str 2 map.
     *
     * @param types the types
     * @return the hash map
     */
    private HashMap<String, Integer> Str2Map (String types){
        String[] type = types.split(";");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String s : type){
            String[] val = s.split(":");
            if (val.length > 1) {
                String type_name = val[0];
                int times = Integer.valueOf(val[1]);
                map.put(type_name, times);
            }
        }

        return map;
    }

    private HashMap<String, Integer> Type2Map (String types){
        if (types.charAt(0) == '['){
            types = types.substring(1);
        }
        if (types.charAt(types.length() - 1) == ']'){
            types = types.substring(0,types.length() - 1);
        }
        String[] type = types.split(",");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String s : type){
            //String[] val = s.split(":");
            if (!map.containsKey(s)) {
                map.put(s, 1);
            }
            else{
                int val = map.get(s);
                map.replace(s,val+1);
            }
        }

        return map;
    }
}
