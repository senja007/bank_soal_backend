package com.doyatama.university.model;

public class Chapter {
    private String id;
    private String name;
    private String description;
    private String subject_id;

    public Chapter() {
    }

    public Chapter(String id, String name, String description, String subject_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject_id = subject_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse_id() {
        return subject_id;
    }

    public void setCourse_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public boolean isValid() {
        return this.id != null && this.name != null && this.description != null && this.subject_id != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "name":
                this.name = value;
                break;
            case "description":
                this.description = value;
                break;
            case "subject_id":
                this.subject_id = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
