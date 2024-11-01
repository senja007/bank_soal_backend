package com.doyatama.university.payload;


public class SeasonRequest {
    private String idSeason;
    private String bidangKeahlian_id;
    private String programKeahlian_id;
    private String konsentrasiKeahlian_id;
    private String kelas_id;
    private String tahunAjaran_id;
    private String student_id;
    private String mapel_id;
    private String lecture_id;

    public SeasonRequest() {
    }

    public SeasonRequest(String idSeason, String bidangKeahlian_id, String programKeahlian_id, 
            String konsentrasiKeahlian_id, String kelas_id, String tahunAjaran_id, String student_id, String mapel_id, String lecture_id) {
        this.idSeason = idSeason;
        this.bidangKeahlian_id = bidangKeahlian_id;
        this.programKeahlian_id = programKeahlian_id;
        this.konsentrasiKeahlian_id = konsentrasiKeahlian_id;
        this.kelas_id = kelas_id;
        this.tahunAjaran_id = tahunAjaran_id;
        this.student_id = student_id;
        this.mapel_id = mapel_id;
        this.lecture_id = lecture_id;
    }

    

    public String getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(String idSeason) {
        this.idSeason = idSeason;
    }

    public String getBidangKeahlian_id() {
        return bidangKeahlian_id;
    }

    public void setBidangKeahlian_id(String bidangKeahlian_id) {
        this.bidangKeahlian_id = bidangKeahlian_id;
    }

    public String getProgramKeahlian_id() {
        return programKeahlian_id;
    }

    public void setProgramKeahlian_id(String programKeahlian_id) {
        this.programKeahlian_id = programKeahlian_id;
    }

    public String getKonsentrasiKeahlian_id() {
        return konsentrasiKeahlian_id;
    }

    public void setKonsentrasiKeahlian_id(String konsentrasiKeahlian_id) {
        this.konsentrasiKeahlian_id = konsentrasiKeahlian_id;
    }

    public String getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(String kelas_id) {
        this.kelas_id = kelas_id;
    }

    public String getTahunAjaran_id() {
        return tahunAjaran_id;
    }

    public void setTahunAjaran_id(String tahunAjaran_id) {
        this.tahunAjaran_id = tahunAjaran_id;
    }

    public String getMapel_id() {
        return mapel_id;
    }

    public void setMapel_id(String mapel_id) {
        this.mapel_id = mapel_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }
    
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idSeason":
                this.idSeason = value;
                break;
            case "bidangKeahlian_id":
                this.bidangKeahlian_id = value;
                break;
            case "programKeahlian_id":
                this.programKeahlian_id = value;
                break;
            case "konsentrasiKeahlian_id":
                this.konsentrasiKeahlian_id = value;
                break;
            case "kelas_id":
                this.kelas_id = value;
                break;
            case "tahunAjaran_id":
                this.tahunAjaran_id = value;
                break;
            case "student_id":
                this.student_id = value;
                break;
            case "mapel_id":
                this.mapel_id = value;
                break;
            case "lecture_id":
                this.lecture_id = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
