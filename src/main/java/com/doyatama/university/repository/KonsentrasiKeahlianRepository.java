/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.KonsentrasiKeahlian;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.stereotype.Repository;

/**
 *
 * @author senja
 */
@Repository
public class KonsentrasiKeahlianRepository {
     Configuration conf = HBaseConfiguration.create();
    String tableName = "konsentrasiKeahlians";
    
     public List<KonsentrasiKeahlian> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKonsentrasiKeahlian = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("konsentrasi", "konsentrasi");
        return client.showListTable(tableKonsentrasiKeahlian.toString(), columnMapping, KonsentrasiKeahlian.class, size);
    }
     
     public KonsentrasiKeahlian save(KonsentrasiKeahlian konsentrasiKeahlian) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = konsentrasiKeahlian.getId();
        TableName tableKonsentrasiKeahlian = TableName.valueOf(tableName);
        client.insertRecord(tableKonsentrasiKeahlian, rowKey, "main", "id", rowKey);
        client.insertRecord(tableKonsentrasiKeahlian, rowKey, "main", "konsentrasi", konsentrasiKeahlian.getKonsentrasi());

        client.insertRecord(tableKonsentrasiKeahlian, rowKey, "detail", "created_by", "Doyatama");
        return konsentrasiKeahlian;
    } 
     
         public KonsentrasiKeahlian findById(String BDGid) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKonsentrasiKeahlian = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("konsentrasi", "konsentrasi");

        return client.showDataTable(tableKonsentrasiKeahlian.toString(), columnMapping, BDGid, KonsentrasiKeahlian.class);
    }
         
             public List<KonsentrasiKeahlian> findAllById(List<String> BDGids) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKonsentrasiKeahlian = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("konsentrasi", "konsentrasi");


        List<KonsentrasiKeahlian> konsentrasiKeahlians = new ArrayList<>();
        for (String BDGid : BDGids) {
            KonsentrasiKeahlian konsentrasiKeahlian = client.showDataTable(tableKonsentrasiKeahlian.toString(), columnMapping, BDGid, KonsentrasiKeahlian.class);
            if (konsentrasiKeahlian != null) {
                konsentrasiKeahlians.add(konsentrasiKeahlian);
            }
        }

        return konsentrasiKeahlians;
    }
             
        public KonsentrasiKeahlian update(String BDGid, KonsentrasiKeahlian konsentrasiKeahlian) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableKonsentrasiKeahlian = TableName.valueOf(tableName);
        client.insertRecord(tableKonsentrasiKeahlian, BDGid, "main", "konsentrasi", konsentrasiKeahlian.getKonsentrasi());
        client.insertRecord(tableKonsentrasiKeahlian, BDGid, "detail", "created_by", "Doyatama");
        return konsentrasiKeahlian;
    }
        
        
       public boolean deleteById(String BDGid) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, BDGid);
        return true;
    }  
}
