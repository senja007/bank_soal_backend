
package com.doyatama.university.service;
import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.JadPel;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.Mapel;
import com.doyatama.university.payload.JadPelRequest;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.JadPelRepository;
import com.doyatama.university.repository.LectureRepository;
import com.doyatama.university.repository.MapelRepository;
import com.doyatama.university.util.AppConstants;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JadPelService {
    private JadPelRepository jadPelRepository = new JadPelRepository();
    private MapelRepository mapelRepository = new MapelRepository();
    private LectureRepository lectureRepository = new LectureRepository();
    
    private static final Logger logger = LoggerFactory.getLogger(JadPelService.class);

    public PagedResponse<JadPel> getAllJadPel(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<JadPel> jadPelResponse = jadPelRepository.findAll(size);

        return new PagedResponse<>(jadPelResponse, jadPelResponse.size(), "Successfully get data", 200);
    }

    public JadPel createJadPel(JadPelRequest jadPelRequest) throws IOException {
        Lecture lecture = lectureRepository.findById(jadPelRequest.getLecture_id());
        Mapel mapel = mapelRepository.findById(jadPelRequest.getMapel_id());
        JadPel jadPel = new JadPel();
            jadPel.setIdJadwal(jadPelRequest.getIdJadwal());
            jadPel.setLecture(lecture);
            jadPel.setJabatan(jadPelRequest.getJabatan());
            jadPel.setMapel(mapel);
            jadPel.setJmlJam(jadPelRequest.getJmlJam());
            
            return jadPelRepository.save(jadPel);
    }        

    public DefaultResponse<JadPel> getJadPelById(String jadwalId) throws IOException {
        // Retrieve JadPel
        JadPel jadPel = jadPelRepository.findById(jadwalId);
        return new DefaultResponse<>(jadPel.isValid() ? jadPel : null, jadPel.isValid() ? 1 : 0, "Successfully get data");
    }
    
    public JadPel updateJadPel(String jadwalId, JadPelRequest jadPelRequest) throws IOException {
        Lecture lecture = lectureRepository.findById(jadPelRequest.getLecture_id());
        Mapel mapel = mapelRepository.findById(jadPelRequest.getMapel_id());
        JadPel jadPel = new JadPel();
            jadPel.setIdJadwal(jadPelRequest.getIdJadwal());
            jadPel.setLecture(lecture);
            jadPel.setJabatan(jadPelRequest.getJabatan());
            jadPel.setMapel(mapel);
            jadPel.setJmlJam(jadPelRequest.getJmlJam());
            return jadPelRepository.update(jadwalId, jadPel);
    }
    
    public void deleteJadPelById(String jadwalId) throws IOException {
        JadPel jadPelResponse = jadPelRepository.findById(jadwalId);
        if(jadPelResponse.isValid()){
            jadPelRepository.deleteById(jadwalId);
        }else{
            throw new ResourceNotFoundException("JadPel", "id", jadwalId);
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
