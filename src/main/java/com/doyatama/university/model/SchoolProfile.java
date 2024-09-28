package com.doyatama.university.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SchoolProfile {
    private String id;
    
    private String title;
    private String description;
    private String schoolId;
    private ProfileType type;
    private String file_path;
    
    public enum ProfileType{
        IMAGE,
        AUDIO,
        VIDEO,
        NORMAL,
    }
    
    public SchoolProfile(){}

    public SchoolProfile(String id, String title, String description, String schoolId, ProfileType type, String file_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.schoolId = schoolId;
        this.type = type;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public ProfileType getType() {
        return type;
    }

    public void setType(ProfileType type) {
        this.type = type;
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
               this.description != null &&
               this.schoolId != null;
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
            case "schoolId":
                this.schoolId = value;
                break;
            case "type":
                this.type = ProfileType.valueOf(value);
                break;
            case "file_path":
                this.file_path = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
