package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.JadPel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.stereotype.Repository;

@Repository
public class JadPelRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "jadwalPelajaran";
    
    public List<JadPel> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableJadPel = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idJadwal", "idJadwal");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("jabatan", "jabatan");
        columnMapping.put("mapel", "mapel");
        columnMapping.put("jmlJam", "jmlJam");
        return client.showListTable(tableJadPel.toString(), columnMapping, JadPel.class, size);
    }
     
    public JadPel save(JadPel jadpel) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = jadpel.getIdJadwal();
        TableName tableJadPel = TableName.valueOf(tableName);
        client.insertRecord(tableJadPel, rowKey, "main", "idJadPel", rowKey);
        client.insertRecord(tableJadPel, rowKey, "main", "jabatan", jadpel.getJabatan());
        client.insertRecord(tableJadPel, rowKey, "main", "jmlJam", jadpel.getJmlJam());
        client.insertRecord(tableJadPel, rowKey, "lecture", "id", jadpel.getLecture().getId());
        client.insertRecord(tableJadPel, rowKey, "lecture", "name", jadpel.getLecture().getName());
        client.insertRecord(tableJadPel, rowKey, "lecture", "nip", jadpel.getLecture().getNip());
        client.insertRecord(tableJadPel, rowKey, "mapel", "idMapel", jadpel.getMapel().getIdMapel());
        client.insertRecord(tableJadPel, rowKey, "mapel", "name", jadpel.getMapel().getName());
        client.insertRecord(tableJadPel, rowKey, "detail", "created_by", "Doyatama");
        return jadpel;
    } 
     
    public JadPel findById(String jadwalId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableJadPel = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idJadwal", "idJadwal");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("jabatan", "jabatan");
        columnMapping.put("mapel", "mapel");
        columnMapping.put("jmlJam", "jmlJam");

        return client.showDataTable(tableJadPel.toString(), columnMapping, jadwalId, JadPel.class);
    }
         
    public List<JadPel> findAllById(List<String> jadwalIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableJadPel = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idJadwal", "idJadwal");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("jabatan", "jabatan");
        columnMapping.put("mapel", "mapel");
        columnMapping.put("jmlJam", "jmlJam");
        
        List<JadPel> jadpels = new ArrayList<>();
        for (String jadwalId : jadwalIds) {
            JadPel jadpel = client.showDataTable(tableJadPel.toString(), columnMapping, jadwalId, JadPel.class);
            if (jadpel != null) {
                jadpels.add(jadpel);
            }
        }

        return jadpels;
    }
    
    public List<List<JadPel>> findAllById2D(List<List<String>> jadwalIdGroups) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableJadPel = TableName.valueOf(tableName);

        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idJadwal", "idJadwal");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("jabatan", "jabatan");
        columnMapping.put("mapel", "mapel");
        columnMapping.put("jmlJam", "jmlJam");

        List<List<JadPel>> jadwals2D = new ArrayList<>();

        for (List<String> jadwalIds : jadwalIdGroups) {
            List<JadPel> jadwalRow = new ArrayList<>();
            for (String jadwalId : jadwalIds) {
                JadPel jadpel = client.showDataTable(tableJadPel.toString(), columnMapping, jadwalId, JadPel.class);
                if (jadpel != null) {
                    jadwalRow.add(jadpel);
                }
            }
            jadwals2D.add(jadwalRow);
        }

        return jadwals2D;
    }

             
    public JadPel update(String jadwalId, JadPel jadpel) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableJadPel = TableName.valueOf(tableName);
        client.insertRecord(tableJadPel, jadwalId, "main", "jabatan", jadpel.getJabatan());
        client.insertRecord(tableJadPel, jadwalId, "main", "jmlJam", jadpel.getJmlJam());
        client.insertRecord(tableJadPel, jadwalId, "lecture", "id", jadpel.getLecture().getId());
        client.insertRecord(tableJadPel, jadwalId, "lecture", "name", jadpel.getLecture().getName());
        client.insertRecord(tableJadPel, jadwalId, "lecture", "nip", jadpel.getLecture().getNip());
        client.insertRecord(tableJadPel, jadwalId, "mapel", "idMapel", jadpel.getMapel().getIdMapel());
        client.insertRecord(tableJadPel, jadwalId, "mapel", "name", jadpel.getMapel().getName());
        client.insertRecord(tableJadPel, jadwalId, "detail", "created_by", "Doyatama");
        return jadpel;
    }
    
    public boolean deleteById(String jadwalId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, jadwalId);
        return true;
    }  
}
