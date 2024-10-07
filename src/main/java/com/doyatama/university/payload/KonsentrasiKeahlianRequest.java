package com.doyatama.university.payload;

public class KonsentrasiKeahlianRequest {
    
    private String id;
    private String konsentrasi; 
    
    public KonsentrasiKeahlianRequest() {
    }
    
    public KonsentrasiKeahlianRequest(String id, String konsentrasi) {
        this.id = id;
        this.konsentrasi = konsentrasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKonsentrasi() {
        return konsentrasi;
    }

    public void setKonsentrasi(String konsentrasi) {
        this.konsentrasi = konsentrasi;
    }

  

    public boolean isValid() {
        return this.id != null && this.konsentrasi != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "konsentrasi":
                this.konsentrasi = value;  
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
