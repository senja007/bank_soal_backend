/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doyatama.university.payload;

/**
 *
 * @author senja
 */
public class MapelRequest {
    private String id;
    private String name;
    private String konsentrasiKeahlian_id;

    public MapelRequest() {
    }

    public MapelRequest(String id, String name, String konsentrasiKeahlian_id) {
        this.id = id;
        this.name = name;
        this.konsentrasiKeahlian_id = konsentrasiKeahlian_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKonsentrasiKeahlian_id() {
        return konsentrasiKeahlian_id;
    }

    public void setKonsentrasiKeahlian_id(String konsentrasiKeahlian_id) {
        this.konsentrasiKeahlian_id = konsentrasiKeahlian_id;
    }

 
    
     public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "name":
                this.name = value;  
                break;
            case "konsentrasiKeahlianKeahlian_id":
                this.konsentrasiKeahlian_id = value;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
