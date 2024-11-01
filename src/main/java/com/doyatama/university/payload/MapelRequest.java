
package com.doyatama.university.payload;

public class MapelRequest {
    private String idMapel;
    private String name;
    private String konsentrasiKeahlian_id;

    public MapelRequest() {
    }

    public MapelRequest(String idMapel, String name, String konsentrasiKeahlian_id) {
        this.idMapel = idMapel;
        this.name = name;
        this.konsentrasiKeahlian_id = konsentrasiKeahlian_id;
    }

    public String getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(String idMapel) {
        this.idMapel = idMapel;
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
            case "idMapel":
                this.idMapel = value;
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
