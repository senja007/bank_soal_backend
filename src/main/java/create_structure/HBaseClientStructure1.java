package create_structure;

import com.github.javafaker.Faker;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HBaseClientStructure1 {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

        // ==============================================================================================
        // CREATE COLLECTION
        // ==============================================================================================
        
       
        // Create Tabel Season
        TableName tableSeason = TableName.valueOf("seasons");
        String[] season = { "main", "bidangKeahlian", "programKeahlian", "konsentrasiKeahlian", "kelas", "tahunAjaran", "student", "mapel", "lecture", "detail" };
        client.deleteTable(tableSeason);
        client.createTable(tableSeason, season);

    }
}