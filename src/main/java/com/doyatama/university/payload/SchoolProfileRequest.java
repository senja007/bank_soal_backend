package com.doyatama.university.payload;


public class SchoolProfileRequest {
    private String id;
    
    private String title;
    private String description;
    private String school_id;

    public SchoolProfileRequest() {
    }

    public SchoolProfileRequest(String id, String title, String description, String school_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.school_id = school_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }
    
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "title":
                this.title = value;
                break;
            case "description":
                this.description = value;
                break;
            case "school_id":
                this.school_id = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
