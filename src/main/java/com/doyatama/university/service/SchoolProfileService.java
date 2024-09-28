package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.SchoolProfile;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.SchoolProfileRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.SchoolProfileRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolProfileService {
    private SchoolProfileRepository schoolProfileRepository = new SchoolProfileRepository();
    
    private static final Logger logger = LoggerFactory.getLogger(SchoolProfileService.class);
    
    public PagedResponse<SchoolProfile> getAllProfile(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<SchoolProfile> profileResponse = new ArrayList<>();

        
            profileResponse = schoolProfileRepository.findAll(size);
        

        return new PagedResponse<>(profileResponse, profileResponse.size(), "Successfully get data", 200);
    }
    
    public SchoolProfile createSchoolProfile(SchoolProfileRequest profileRequest, String savePath) throws IOException {
        SchoolProfile profile = new SchoolProfile();
            profile.setTitle(profileRequest.getTitle());
            profile.setDescription(profileRequest.getDescription());
            profile.setType(SchoolProfile.ProfileType.valueOf(profileRequest.getType()));
            profile.setFile_path(savePath);
            return schoolProfileRepository.save(profile);
    }
    
    public DefaultResponse<SchoolProfile> getSchoolProfileById(String profileId) throws IOException {
        // Retrieve SchoolProfile
        SchoolProfile profileResponse = schoolProfileRepository.findById(profileId);
        return new DefaultResponse<>(profileResponse.isValid() ? profileResponse : null, profileResponse.isValid() ? 1 : 0, "Successfully get data");
    }
    
    public SchoolProfile updateSchoolProfile(String profileId, SchoolProfileRequest profileRequest, String savePath) throws IOException {
        SchoolProfile profile = new SchoolProfile();
            profile.setTitle(profileRequest.getTitle());
            profile.setDescription(profileRequest.getDescription());
            profile.setType(SchoolProfile.ProfileType.valueOf(profileRequest.getType()));
            profile.setFile_path(savePath);
            return schoolProfileRepository.update(profileId, profile);
    }
    
    public void deleteSchoolProfileById(String profileId) throws IOException {
        SchoolProfile profileResponse = schoolProfileRepository.findById(profileId);
        if(profileResponse.isValid()){
            schoolProfileRepository.deleteById(profileId);
        }else{
            throw new ResourceNotFoundException("SchoolProfile", "id", profileId);
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
