package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Place;
import com.example.accessingdatamysql.model.userprofile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * The Class userprofileService.
 */
@Service
public class userprofileService {
    
    /** The userprofile repository. */
    @Autowired
    private userprofileRepository userprofileRepository;

    /**
     * Update userprofile.
     *
     * @param userprofile the userprofile
     */
    public void updateUserprofile(userprofile userprofile){
        userprofileRepository.save(userprofile);
    }

    /**
     * Gets the userprofile by name.
     *
     * @param name the name
     * @return the userprofile by name
     */
    public userprofile getUserprofileByName(String name){
        return userprofileRepository.findFirstByName(name);
    }

    /**
     * Update userprofile.
     *
     * @param username the username
     * @param place the place
     */
    public void updateUserprofile(String username, Place place){
        userprofile userprofile = userprofileRepository.findFirstByName(username);
        int totalHistoryNum = userprofile.getTotal_history();
        userprofile.setTotal_history(totalHistoryNum+1);
        double avg_priceLevel = userprofile.getAvg_price();
        avg_priceLevel = (avg_priceLevel*totalHistoryNum + place.getPrice_level())
                / (totalHistoryNum+1);
        userprofile.setAvg_price(avg_priceLevel);

        HashMap<String, Integer> typeMap = Str2Map(userprofile.getTypes());
        ArrayList<String> newTypeMap = Str2List(place.getTypes());
        for (String k : newTypeMap){
            if (typeMap.containsKey(k)){
                typeMap.put(k, typeMap.get(k) + 1);
            }
            else{
                typeMap.put(k, 1);
            }
        };
        userprofile.setTypes(Map2Str(typeMap));
        userprofileRepository.save(userprofile);
    }

    /**
     * Str 2 list.
     *
     * @param types the types
     * @return the array list
     */
    private ArrayList<String> Str2List (String types) {
        ArrayList<String> list = new ArrayList<String>();
        if (types == null){
            return list;
        }
        String[] type = types.split(",");
        for (String s : type) {
            list.add(s);
        }
        return list;
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
    
    /**
     * Map 2 str.
     *
     * @param types the types
     * @return the string
     */
    private String Map2Str (HashMap<String, Integer> types){
        String type = "";
        for (Map.Entry mapElement : types.entrySet()) {
            String k = (String)mapElement.getKey();
            Integer v = (Integer)mapElement.getValue();
            type += k + ":"+v.toString() + ";";
        }

        return type;
    }
}
