package com.doyatama.university.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.SchoolProfile;
import java.io.IOException;
import java.util.*;

public class SchoolProfileRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "school-profiles";
    
    public List<SchoolProfile> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName profile = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("type", "type");
        columnMapping.put("file_path", "file_path");
        return client.showListTable(profile.toString(), columnMapping, SchoolProfile.class, size);
    }
    
    public SchoolProfile save(SchoolProfile profile) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString();
        TableName tableProfile = TableName.valueOf(tableName);
        client.insertRecord(tableProfile, rowKey, "main", "id", rowKey);
        client.insertRecord(tableProfile, rowKey, "main", "title", profile.getTitle());
        client.insertRecord(tableProfile, rowKey, "main", "description", profile.getDescription());
        client.insertRecord(tableProfile, rowKey, "main", "type", profile.getType().toString());
        client.insertRecord(tableProfile, rowKey, "main", "file_path", profile.getFile_path());
        client.insertRecord(tableProfile, rowKey, "detail", "created_by", "Doyatama");
        return profile;
    }
    
    public SchoolProfile findById(String profileId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableProfile = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("type", "type");
        columnMapping.put("file_path", "file_path");

        return client.showDataTable(tableProfile.toString(), columnMapping, profileId, SchoolProfile.class);
    }
    
    public List<SchoolProfile> findAllById(List<String> profileIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableProfile = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("type", "type");
        columnMapping.put("is_right", "is_right");
        columnMapping.put("file_path", "file_path");

        List<SchoolProfile> profiles = new ArrayList<>();
        for (String profileId : profileIds) {
            SchoolProfile profile = client.showDataTable(tableProfile.toString(), columnMapping, profileId, SchoolProfile.class);
            if (profile != null) {
                profiles.add(profile);
            }
        }

        return profiles;
    }
    
    public SchoolProfile update(String profileId, SchoolProfile profile) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableProfile = TableName.valueOf(tableName);
        client.insertRecord(tableProfile, profileId, "main", "title", profile.getTitle());
        client.insertRecord(tableProfile, profileId, "main", "description", profile.getDescription());
        client.insertRecord(tableProfile, profileId, "main", "type", profile.getType().toString());
        client.insertRecord(tableProfile, profileId, "main", "file_path", profile.getFile_path());
        client.insertRecord(tableProfile, profileId, "detail", "created_by", "Doyatama");
        return profile;
    }
    
    public boolean deleteById(String profileId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, profileId);
        return true;
    }
}
