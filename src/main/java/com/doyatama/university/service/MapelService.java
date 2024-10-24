/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.KonsentrasiKeahlian;
import com.doyatama.university.model.Mapel;
import com.doyatama.university.payload.MapelRequest;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.KonsentrasiKeahlianRepository;
import com.doyatama.university.repository.MapelRepository;
import com.doyatama.university.util.AppConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author senja
 */

@Service
public class MapelService {
    private MapelRepository mapelRepository = new MapelRepository();
    private KonsentrasiKeahlianRepository konsentrasiKeahlianRepository = new KonsentrasiKeahlianRepository();
    
    private static final Logger logger = LoggerFactory.getLogger(MapelService.class);

public PagedResponse<Mapel> getAllMapel(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Mapel> mapelResponse = new ArrayList<>();

        
            mapelResponse = mapelRepository.findAll(size);
        

        return new PagedResponse<>(mapelResponse, mapelResponse.size(), "Successfully get data", 200);
    }

    public Mapel createMapel(MapelRequest mapelRequest) throws IOException {
       KonsentrasiKeahlian bidang = konsentrasiKeahlianRepository.findById(mapelRequest.getKonsentrasiKeahlian_id());
        
        Mapel mapel = new Mapel();
            mapel.setId(mapelRequest.getId());
            mapel.setName(mapelRequest.getName());
          mapel.setKonsentrasiKeahlian(bidang);
            return mapelRepository.save(mapel);
    }        

    public DefaultResponse<Mapel> getMapelById(String BDGid) throws IOException {
        // Retrieve Mapel
        Mapel mapel = mapelRepository.findById(BDGid);
        return new DefaultResponse<>(mapel.isValid() ? mapel : null, mapel.isValid() ? 1 : 0, "Successfully get data");
    }
    
        public Mapel updateMapel(String BDGid, MapelRequest mapelRequest) throws IOException {
        Mapel mapel = new Mapel();
            mapel.setName(mapelRequest.getName());
            return mapelRepository.update(BDGid, mapel);
    }
    
    public void deleteMapelById(String BDGid) throws IOException {
        Mapel mapelResponse = mapelRepository.findById(BDGid);
        if(mapelResponse.isValid()){
            mapelRepository.deleteById(BDGid);
        }else{
            throw new ResourceNotFoundException("Mapel", "id", BDGid);
        }
    }



    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
