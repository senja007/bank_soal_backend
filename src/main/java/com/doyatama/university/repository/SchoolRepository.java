package com.doyatama.university.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.School;
import java.io.IOException;
import java.util.*;

public class SchoolRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "schools";
    
    public List<School> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName school = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("address", "address");
        return client.showListTable(school.toString(), columnMapping, School.class, size);
    }
    
    public School save(School school) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = school.getId();
        TableName tableSchool = TableName.valueOf(tableName);
        client.insertRecord(tableSchool, rowKey, "main", "id", rowKey);
        client.insertRecord(tableSchool, rowKey, "main", "name", school.getName());
        client.insertRecord(tableSchool, rowKey, "main", "address", school.getAddress());
        client.insertRecord(tableSchool, rowKey, "detail", "created_by", "Doyatama");
        return school;
    }
    
    public School findById(String schoolId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSchool = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("address", "address");

        return client.showDataTable(tableSchool.toString(), columnMapping, schoolId, School.class);
    }
    
    public List<School> findAllById(List<String> schoolIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSchool = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("address", "address");

        List<School> schools = new ArrayList<>();
        for (String schoolId : schoolIds) {
            School school = client.showDataTable(tableSchool.toString(), columnMapping, schoolId, School.class);
            if (school != null) {
                schools.add(school);
            }
        }

        return schools;
    }
    
    public School update(String schoolId, School school) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableSchool = TableName.valueOf(tableName);
        client.insertRecord(tableSchool, schoolId, "main", "name", school.getName());
        client.insertRecord(tableSchool, schoolId, "main", "address", school.getAddress());
        client.insertRecord(tableSchool, schoolId, "detail", "created_by", "Doyatama");
        return school;
    }
    
    public boolean deleteById(String schoolId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, schoolId);
        return true;
    }
}
