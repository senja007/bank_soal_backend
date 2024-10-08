package com.doyatama.university.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SchoolProfile {
    private String id;
    
    private String title;
    private String description;
    private School school;
    private String file_path;
    
    public SchoolProfile(){}

    public SchoolProfile(String id, String title, String description, School school,  String file_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.school = school;
        this.file_path = file_path;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    
    public boolean isValid(){
        return this.id != null &&
               this.title != null &&
               this.description != null;
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
            case "file_path":
                this.file_path = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
