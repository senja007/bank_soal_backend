package com.doyatama.university.model;




public class ProgramKeahlian {
    

    private String id;
    private String program;  // Mengubah field ke camelCase
    
    public ProgramKeahlian() {
    }
    
    public ProgramKeahlian(String id, String program){
        this.id = id;
        this.program = program;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    
    
    public boolean isValid() {
        return this.id != null && this.program != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "program":
                this.program = value;  
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
