package com.doyatama.university.payload;


public class SchoolProfileRequest {
    private String id;
    
    private String title;
    private String description;
    private String schoolId;
    private String type;

    public SchoolProfileRequest() {
    }

    public SchoolProfileRequest(String id, String title, String description, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
            case "type":
                this.type = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
