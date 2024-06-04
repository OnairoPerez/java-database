/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

import java.util.Arrays;

/**
 *
 * @author onairo
 */
public class Json {
    private String key;
    private String value;
    private int intValue;

    public Json() {}
    public Json(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public Json(String key, int value) {
        this.key = key;
        this.intValue = value;
    }
    
    //Getters
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public int getIntValue() {
        return intValue;
    }
    
    public String queryString(Json[] jsonArray) {
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < jsonArray.length; i++) {
            Json json = jsonArray[i];
            if (json.getValue() == null) {
                resultBuilder.append(json.getKey()).append(" = ").append(json.getIntValue());
            } else {
                resultBuilder.append(json.getKey()).append(" = ").append("\"").append(json.getValue()).append("\"");
            }

            if (i < jsonArray.length - 1) {
                resultBuilder.append(", ");
            } else if (i == jsonArray.length - 1) {
                resultBuilder.append(" ");
            }
        }

        return resultBuilder.toString();
    }
}
