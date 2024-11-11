/*
 * Click nbfs://nbhost/SystemFileSystem/Teseasonates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Teseasonates/Classes/Class.java to edit this teseasonate
 */
package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.JadPel;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.Mapel;
import com.doyatama.university.model.Season;
import com.doyatama.university.model.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.stereotype.Repository;

/**
 *
 * @author senja
 */
@Repository
public class SeasonRepository {
     Configuration conf = HBaseConfiguration.create();
    String tableName = "seasons";
    
    public List<Season> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSeason = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idSeason", "idSeason");
        columnMapping.put("bidangKeahlian", "bidangKeahlian");
        columnMapping.put("programKeahlian", "programKeahlian");
        columnMapping.put("konsentrasiKeahlian", "konsentrasiKeahlian");
        columnMapping.put("kelas", "kelas");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("tahunAjaran", "tahunAjaran");
        columnMapping.put("student", "student");
        columnMapping.put("jadwalPelajaran", "jadwalPelajaran");
        return client.showListTable(tableSeason.toString(), columnMapping, Season.class, size);
    }
     
    public Season save(Season season) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString();
        TableName tableSeason = TableName.valueOf(tableName);
        client.insertRecord(tableSeason, rowKey, "main", "idSeason", rowKey);
        client.insertRecord(tableSeason, rowKey, "bidangKeahlian", "id", season.getBidangKeahlian().getId());
        client.insertRecord(tableSeason, rowKey, "bidangKeahlian", "bidang", season.getBidangKeahlian().getBidang());
        client.insertRecord(tableSeason, rowKey, "programKeahlian", "id", season.getProgramKeahlian().getId());
        client.insertRecord(tableSeason, rowKey, "programKeahlian", "program", season.getProgramKeahlian().getProgram());
        client.insertRecord(tableSeason, rowKey, "konsentrasiKeahlian", "id", season.getKonsentrasiKeahlian().getId());
        client.insertRecord(tableSeason, rowKey, "konsentrasiKeahlian", "konsentrasi", season.getKonsentrasiKeahlian().getKonsentrasi());
        client.insertRecord(tableSeason, rowKey, "kelas", "idKelas", season.getKelas().getIdKelas());
        client.insertRecord(tableSeason, rowKey, "kelas", "namaKelas", season.getKelas().getNamaKelas());
        client.insertRecord(tableSeason, rowKey, "tahunAjaran", "idTahun", season.getTahunAjaran().getIdTahun());
        client.insertRecord(tableSeason, rowKey, "tahunAjaran", "tahunAjaran", season.getTahunAjaran().getTahunAjaran());
        client.insertRecord(tableSeason, rowKey, "lecture", "id", season.getLecture().getId());
        client.insertRecord(tableSeason, rowKey, "lecture", "name", season.getLecture().getName());
        client.insertRecord(tableSeason, rowKey, "lecture", "nip", season.getLecture().getNip());

            for (List<Student> studentRow : season.getStudent()) {
                if (!studentRow.isEmpty()) {
                    List<String> studentIds = studentRow.stream()
                        .map(Student::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                    if (!studentIds.isEmpty()) {
                        client.insertListRecord(tableSeason, rowKey, "student", "id", studentIds);
                    }
                }
            }
            for (List<JadPel> jadPelRow : season.getJadPel()) {
                if (!jadPelRow.isEmpty()) {
                    List<String> jadwalIds = jadPelRow.stream()
                        .map(JadPel::getIdJadwal)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                    if (!jadwalIds.isEmpty()) {
                        client.insertListRecord(tableSeason, rowKey, "jadwalPelajaran", "idJadwal", jadwalIds);
                    }
                }
            }




        client.insertRecord(tableSeason, rowKey, "detail", "created_by", "Doyatama");
        return season;
    } 
     
    public Season findById(String seasonId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSeason = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idSeason", "idSeason");
        columnMapping.put("bidangKeahlian", "bidangKeahlian");
        columnMapping.put("programKeahlian", "programKeahlian");
        columnMapping.put("konsentrasiKeahlian", "konsentrasiKeahlian");
        columnMapping.put("kelas", "kelas");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("tahunAjaran", "tahunAjaran");
        columnMapping.put("student", "student");
        columnMapping.put("jadwalPelajaran", "jadwalPelajaran");

        return client.showDataTable(tableSeason.toString(), columnMapping, seasonId, Season.class);
        
    }
         
    public List<Season> findAllById(List<String> seasonIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSeason = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idSeason", "idSeason");
        columnMapping.put("bidangKeahlian", "bidangKeahlian");
        columnMapping.put("programKeahlian", "programKeahlian");
        columnMapping.put("konsentrasiKeahlian", "konsentrasiKeahlian");
        columnMapping.put("kelas", "kelas");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("tahunAjaran", "tahunAjaran");
        columnMapping.put("student", "student");
        columnMapping.put("jadwalPelajaran", "jadwalPelajaran");


        List<Season> seasons = new ArrayList<>();
        for (String seasonId : seasonIds) {
            Season season = client.showDataTable(tableSeason.toString(), columnMapping, seasonId, Season.class);
            if (season != null) {
                seasons.add(season);
            }
        }

        return seasons;
    }
             
    public Season update(String seasonId, Season season) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSeason = TableName.valueOf(tableName);
        
        client.insertRecord(tableSeason, seasonId, "bidangKeahlian", "id", season.getBidangKeahlian().getId());
        client.insertRecord(tableSeason, seasonId, "bidangKeahlian", "bidang", season.getBidangKeahlian().getBidang());
        client.insertRecord(tableSeason, seasonId, "programKeahlian", "id", season.getProgramKeahlian().getId());
        client.insertRecord(tableSeason, seasonId, "programKeahlian", "program", season.getProgramKeahlian().getProgram());
        client.insertRecord(tableSeason, seasonId, "konsentrasiKeahlian", "id", season.getKonsentrasiKeahlian().getId());
        client.insertRecord(tableSeason, seasonId, "konsentrasiKeahlian", "konsentrasi", season.getKonsentrasiKeahlian().getKonsentrasi());
        client.insertRecord(tableSeason, seasonId, "kelas", "idKelas", season.getKelas().getIdKelas());
        client.insertRecord(tableSeason, seasonId, "kelas", "namaKelas", season.getKelas().getNamaKelas());
        client.insertRecord(tableSeason, seasonId, "tahunAjaran", "idTahun", season.getTahunAjaran().getIdTahun());
        client.insertRecord(tableSeason, seasonId, "tahunAjaran", "tahunAjaran", season.getTahunAjaran().getTahunAjaran());
        client.insertRecord(tableSeason, seasonId, "lecture", "id", season.getLecture().getId());
        client.insertRecord(tableSeason, seasonId, "lecture", "name", season.getLecture().getName());
        client.insertRecord(tableSeason, seasonId, "lecture", "nip", season.getLecture().getNip());

        if (season.getStudent() != null) {
            for (List<Student> studentRow : season.getStudent()) {
                for (Student student : studentRow) {
                    if (student != null) {
                        client.insertRecord(tableSeason, seasonId, "student", "id", student.getId());
                        client.insertRecord(tableSeason, seasonId, "student", "nisn", student.getNisn());
                        client.insertRecord(tableSeason, seasonId, "student", "name", student.getName());
                        client.insertRecord(tableSeason, seasonId, "student", "gender", student.getGender());
                        client.insertRecord(tableSeason, seasonId, "student", "phone", student.getPhone());
                        client.insertRecord(tableSeason, seasonId, "student", "birth_date", student.getBirth_date());
                        client.insertRecord(tableSeason, seasonId, "student", "place_born", student.getPlace_born());
                        client.insertRecord(tableSeason, seasonId, "student", "address", student.getAddress());
                        client.insertRecord(tableSeason, seasonId, "student", "religion", student.getReligion().getName());
                    }
                }
            }
        }

        if (season.getJadPel() != null) {
            for (List<JadPel> jadPelRow : season.getJadPel()) {
                for (JadPel jadPel : jadPelRow) {
                    if (jadPel != null) {
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "idJadwal", jadPel.getIdJadwal());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "lecture", jadPel.getLecture().getId());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "lecture", jadPel.getLecture().getName());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "lecture", jadPel.getLecture().getNip());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "jabatan", jadPel.getJabatan());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "mapel", jadPel.getMapel().getName());
                        client.insertRecord(tableSeason, seasonId, "jadwalPelajaran", "jmlJam", jadPel.getJmlJam());
                    }
                }
            }
        }
        client.insertRecord(tableSeason, seasonId, "detail", "created_by", "Doyatama");
        return season;
    }
        
        
    public boolean deleteById(String seasonId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, seasonId);
        return true;
    }  
}
