package com.example.accessingdatamysql;
import com.example.accessingdatamysql.model.Place;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * The Class PlacesService.
 */
public class PlacesService {
    
    /** The Constant LOG_TAG. */
    private static final String LOG_TAG = "ExampleApp";

    /** The Constant PLACES_API_BASE. */
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    /** The Constant TYPE_AUTOCOMPLETE. */
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    
    /** The Constant TYPE_DETAILS. */
    private static final String TYPE_DETAILS = "/details";
    
    /** The Constant TYPE_SEARCH. */
    private static final String TYPE_SEARCH = "/nearbysearch";

    /** The Constant OUT_JSON. */
    private static final String OUT_JSON = "/json";

    /** The Constant API_KEY. */
    // KEY!
    private static final String API_KEY = "";

//    public static ArrayList<Place> autocomplete(String input) {
//        ArrayList<Place> resultList = null;
//
//        HttpURLConnection conn = null;
//        StringBuilder jsonResults = new StringBuilder();
//        try {
//            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
//            sb.append(TYPE_AUTOCOMPLETE);
//            sb.append(OUT_JSON);
//            sb.append("?sensor=false");
//            sb.append("&key=" + API_KEY);
//            sb.append("&input=" + URLEncoder.encode(input, "utf8"));
//
//            URL url = new URL(sb.toString());
//            conn = (HttpURLConnection) url.openConnection();
//            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//
//            int read;
//            char[] buff = new char[1024];
//            while ((read = in.read(buff)) != -1) {
//                jsonResults.append(buff, 0, read);
//            }
//        } catch (MalformedURLException e) {
//            System.out.println("Error processing Places API URL");
//            return resultList;
//        } catch (IOException e) {
//            System.out.println("Error connecting to Places API");
//            return resultList;
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//
//        try {
//            // Create a JSON object hierarchy from the results
//            JSONObject jsonObj = new JSONObject(jsonResults.toString());
//            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
//
//            // Extract the Place descriptions from the results
//            resultList = new ArrayList<Place>(predsJsonArray.length());
//            for (int i = 0; i < predsJsonArray.length(); i++) {
//                Place place = new Place();
//                place.reference = predsJsonArray.getJSONObject(i).getString("reference");
//                place.name = predsJsonArray.getJSONObject(i).getString("description");
//                resultList.add(place);
//            }
//        } catch (JSONException e) {
//            System.out.println("Error processing JSON results");
//        }
//
//        return resultList;
//    }

    /**
 * Nearby search.
 *
 * @param lat the lat
 * @param lng the lng
 * @param radius the radius
 * @return the array list
 */
public static ArrayList<Place> nearbySearch(//String keyword,
                                                double lat, double lng, int radius) {
        ArrayList<Place> resultList = null;
        int totalRatingNum = 0;
        int totalLen = 0;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("?location=" + lat + "," + lng);
            sb.append("&type=restaurant");
            //sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
            sb.append("&radius=" + radius);
            sb.append("&key=" + API_KEY);

            System.out.println(sb);
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            System.out.println("Error processing Places API URL");
            return resultList;
        } catch (IOException e) {
            System.out.println("Error connecting to Places API");
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<Place>(predsJsonArray.length());
            totalLen = predsJsonArray.length();

            for (int i = 0; i < totalLen; i++) {
                Place place = new Place();
                JSONObject geometry = predsJsonArray.getJSONObject(i).getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                place.setLat(location.getDouble("lat"));
                place.setLng(location.getDouble("lng"));

                place.setName(predsJsonArray.getJSONObject(i).getString("name"));
                place.setPlace_id(predsJsonArray.getJSONObject(i).getString("place_id"));
                if (predsJsonArray.getJSONObject(i).has("price_level"))
                    place.setPrice_level(predsJsonArray.getJSONObject(i).getInt("price_level"));
                else{
                    place.setPrice_level(3);
                }
                place.setRating(predsJsonArray.getJSONObject(i).getDouble("rating"));
                if (predsJsonArray.getJSONObject(i).has("user_ratings_total")){
                    int rating_num = predsJsonArray.getJSONObject(i).getInt("user_ratings_total");
                    place.setRating_num(rating_num);
                    totalRatingNum += rating_num;
                }
                else {
                    place.setRating_num(0);
                }

//                JSONArray types = predsJsonArray.getJSONObject(i).getJSONArray("types");
//                ArrayList<String> listdata = new ArrayList<String>();
//                if (types != null) {
//                    for (int j=0;j<types.length();j++){
//                        listdata.add(types.getString(j));
//                    }
//                }
                place.setTypes(predsJsonArray.getJSONObject(i).getString("types"));

                if (predsJsonArray.getJSONObject(i).has("opening_hours")) {
                    JSONObject opening_hours = predsJsonArray.getJSONObject(i).getJSONObject("opening_hours");
                    place.setOpen_now(opening_hours.getBoolean("open_now"));
                }
                else{
                    place.setOpen_now(true);
                }
                resultList.add(place);
            }
        } catch (JSONException e) {
            System.out.println("Error processing JSON results");
        }

        //update rating
        double avgRatingNum = (totalLen == 0) ? 0 : totalRatingNum / totalLen;
        for (int i = 0; i < totalLen; i++) {
            int v = resultList.get(i).getRating_num(); //is the number of votes for the movie;
            double m = avgRatingNum / 2;// m is the minimum votes required to be listed in the chart;
            double R = resultList.get(i).getRating(); //is the average rating of the movie;
            double C = avgRatingNum; //is the mean vote across the whole report

            double modRating = (v / (v + m) * R) + (m / (m + v) * C);
            resultList.get(i).setRating(modRating);
        }

        return resultList;
    }

    /**
     * Details.
     *
     * @param place_id the place id
     * @return the place
     */
    public static Place details(String place_id) {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?place_id=" + URLEncoder.encode(place_id, "utf8"));
            sb.append("&key=" + API_KEY);

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            System.out.println("Error processing Places API URL");
            return null;
        } catch (IOException e) {
            System.out.println("Error connecting to Places API");
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        Place place = null;
        try {
            // Create a JSON object hierarchy from the results
            JSONObject predsJsonArray = new JSONObject(jsonResults.toString()).getJSONObject("result");

            place = new Place();
//            place.icon = jsonObj.getString("icon");
//            place.formatted_address = jsonObj.getString("formatted_address");
//            if (jsonObj.has("formatted_phone_number")) {
//                place.formatted_phone_number = jsonObj.getString("formatted_phone_number");
//            }
            JSONObject geometry = predsJsonArray.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            place.setLat(location.getDouble("lat"));
            place.setLng(location.getDouble("lng"));

            place.setName(predsJsonArray.getString("name"));
            place.setPlace_id(predsJsonArray.getString("place_id"));
            place.setPrice_level(predsJsonArray.getInt("price_level"));
            place.setRating(predsJsonArray.getDouble("rating"));

            JSONArray types = predsJsonArray.getJSONArray("types");
            String typeData = "";
            if (types != null) {
                for (int j=0;j<types.length();j++){
                    String curType = types.getString(j);
                    if (!curType.equals("restaurant") || !curType.equals("food"))
                        typeData += curType + ",";
                }
            }
            place.setTypes(typeData);

            JSONObject opening_hours = predsJsonArray.getJSONObject("opening_hours");
            place.setOpen_now(opening_hours.getBoolean("open_now"));
        } catch (JSONException e) {
            System.out.println("Error processing JSON results");
        }

        return place;
    }
}
