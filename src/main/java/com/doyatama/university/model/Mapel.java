/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doyatama.university.model;

/**
 *
 * @author senja
 */
public class Mapel {
    private String idMapel;
    private String name;
    private KonsentrasiKeahlian konsentrasiKeahlian;
    
    public Mapel(){   
    }

    public Mapel(String idMapel, String name, KonsentrasiKeahlian konsentrasiKeahlian) {
        this.idMapel = idMapel;
        this.name = name;
        this.konsentrasiKeahlian = konsentrasiKeahlian;
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

    public KonsentrasiKeahlian getKonsentrasiKeahlian() {
        return konsentrasiKeahlian;
    }

    public void setKonsentrasiKeahlian(KonsentrasiKeahlian konsentrasiKeahlian) {
        this.konsentrasiKeahlian = konsentrasiKeahlian;
    }
    
      public boolean isValid() {
        return this.idMapel != null && this.konsentrasiKeahlian != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idMapel":
                this.idMapel = value;
                break;
            case "name":
                this.name = value;  
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
    
    
}
