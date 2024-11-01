package com.doyatama.university.model;


public class Season {
    private String idSeason;
    private BidangKeahlian bidangKeahlian;
    private ProgramKeahlian programKeahlian;
    private KonsentrasiKeahlian konsentrasiKeahlian;
    private Kelas kelas;
    private TahunAjaran tahunAjaran;
    private Student student;
    private Mapel mapel;
    private Lecture lecture;

    public Season() {
    }

    public Season(String idSeason, BidangKeahlian bidangKeahlian, ProgramKeahlian programKeahlian, 
            KonsentrasiKeahlian konsentrasiKeahlian, Kelas kelas, TahunAjaran tahunAjaran, Student student, Mapel mapel, Lecture lecture) {
        this.idSeason = idSeason;
        this.bidangKeahlian = bidangKeahlian;
        this.programKeahlian = programKeahlian;
        this.konsentrasiKeahlian = konsentrasiKeahlian;
        this.kelas = kelas;
        this.tahunAjaran = tahunAjaran;
        this.student = student;
        this.mapel = mapel;
        this.lecture = lecture;
    }

    

    public String getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(String idSeason) {
        this.idSeason = idSeason;
    }

    public BidangKeahlian getBidangKeahlian() {
        return bidangKeahlian;
    }

    public void setBidangKeahlian(BidangKeahlian bidangKeahlian) {
        this.bidangKeahlian = bidangKeahlian;
    }

    public ProgramKeahlian getProgramKeahlian() {
        return programKeahlian;
    }

    public void setProgramKeahlian(ProgramKeahlian programKeahlian) {
        this.programKeahlian = programKeahlian;
    }

    public KonsentrasiKeahlian getKonsentrasiKeahlian() {
        return konsentrasiKeahlian;
    }

    public void setKonsentrasiKeahlian(KonsentrasiKeahlian konsentrasiKeahlian) {
        this.konsentrasiKeahlian = konsentrasiKeahlian;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    public TahunAjaran getTahunAjaran() {
        return tahunAjaran;
    }

    public void setTahunAjaran(TahunAjaran tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Mapel getMapel() {
        return mapel;
    }

    public void setMapel(Mapel mapel) {
        this.mapel = mapel;
    }
    
    public boolean isValid() {
        return this.idSeason != null;
    }
    
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idSeason":
                this.idSeason = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
