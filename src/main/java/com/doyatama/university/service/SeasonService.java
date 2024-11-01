package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.BidangKeahlian;
import com.doyatama.university.model.Kelas;
import com.doyatama.university.model.KonsentrasiKeahlian;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.Mapel;
import com.doyatama.university.model.ProgramKeahlian;
import com.doyatama.university.model.Season;
import com.doyatama.university.model.Student;
import com.doyatama.university.model.TahunAjaran;
import com.doyatama.university.payload.SeasonRequest;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.BidangKeahlianRepository;
import com.doyatama.university.repository.KelasRepository;
import com.doyatama.university.repository.KonsentrasiKeahlianRepository;
import com.doyatama.university.repository.LectureRepository;
import com.doyatama.university.repository.MapelRepository;
import com.doyatama.university.repository.ProgramKeahlianRepository;
import com.doyatama.university.repository.SeasonRepository;
import com.doyatama.university.repository.StudentRepository;
import com.doyatama.university.repository.TahunAjaranRepository;
import com.doyatama.university.util.AppConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SeasonService {
    private SeasonRepository seasonRepository = new SeasonRepository();
    private BidangKeahlianRepository bidangKeahlianRepository = new BidangKeahlianRepository();
    private ProgramKeahlianRepository programKeahlianRepository = new ProgramKeahlianRepository();
    private KonsentrasiKeahlianRepository konsentrasiKeahlianRepository = new KonsentrasiKeahlianRepository();
    private KelasRepository kelasRepository = new KelasRepository();
    private TahunAjaranRepository tahunRepository = new TahunAjaranRepository();
    private StudentRepository studentRepository = new StudentRepository();
    private MapelRepository mapelRepository = new MapelRepository();
    private LectureRepository lectureRepository = new LectureRepository();
    
    
    private static final Logger logger = LoggerFactory.getLogger(SeasonService.class);

    public PagedResponse<Season> getAllSeason(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Season> seasonResponse = new ArrayList<>();
        seasonResponse = seasonRepository.findAll(size);
        

        return new PagedResponse<>(seasonResponse, seasonResponse.size(), "Successfully get data", 200);
    }

    public Season createSeason(SeasonRequest seasonRequest) throws IOException {
        BidangKeahlian bidang = bidangKeahlianRepository.findById(seasonRequest.getBidangKeahlian_id());
        ProgramKeahlian program = programKeahlianRepository.findById(seasonRequest.getProgramKeahlian_id());
        KonsentrasiKeahlian konsentrasi = konsentrasiKeahlianRepository.findById(seasonRequest.getKonsentrasiKeahlian_id());
        Kelas kelas = kelasRepository.findById(seasonRequest.getKelas_id());
        TahunAjaran tahun = tahunRepository.findById(seasonRequest.getTahunAjaran_id());
        Student siswa = studentRepository.findById(seasonRequest.getStudent_id());
        Mapel mapel = mapelRepository.findById(seasonRequest.getMapel_id());
        Lecture guru = lectureRepository.findById(seasonRequest.getLecture_id());
        
        Season season = new Season();
            season.setIdSeason(seasonRequest.getIdSeason());
            season.setBidangKeahlian(bidang);
            season.setProgramKeahlian(program);
            season.setKonsentrasiKeahlian(konsentrasi);
            season.setKelas(kelas);
            season.setTahunAjaran(tahun);
            season.setStudent(siswa);
            season.setMapel(mapel);
            season.setLecture(guru);
            
            return seasonRepository.save(season);
    }        

    public DefaultResponse<Season> getSeasonById(String seasonId) throws IOException {
        // Retrieve Season
        Season season = seasonRepository.findById(seasonId);
        return new DefaultResponse<>(season.isValid() ? season : null, season.isValid() ? 1 : 0, "Successfully get data");
    }
    
    public Season updateSeason(String seasonId, SeasonRequest seasonRequest) throws IOException {
        BidangKeahlian bidang = bidangKeahlianRepository.findById(seasonRequest.getBidangKeahlian_id());
        ProgramKeahlian program = programKeahlianRepository.findById(seasonRequest.getProgramKeahlian_id());
        KonsentrasiKeahlian konsentrasi = konsentrasiKeahlianRepository.findById(seasonRequest.getKonsentrasiKeahlian_id());
        Kelas kelas = kelasRepository.findById(seasonRequest.getKelas_id());
        TahunAjaran tahun = tahunRepository.findById(seasonRequest.getTahunAjaran_id());
        Student siswa = studentRepository.findById(seasonRequest.getStudent_id());
        Mapel mapel = mapelRepository.findById(seasonRequest.getMapel_id());
        Lecture guru = lectureRepository.findById(seasonRequest.getLecture_id());
        
        Season season = new Season();
            season.setBidangKeahlian(bidang);
            season.setProgramKeahlian(program);
            season.setKonsentrasiKeahlian(konsentrasi);
            season.setKelas(kelas);
            season.setTahunAjaran(tahun);
            season.setStudent(siswa);
            season.setMapel(mapel);
            season.setLecture(guru);
            return seasonRepository.update(seasonId, season);
    }
    
    public void deleteSeasonById(String seasonId) throws IOException {
        Season seasonResponse = seasonRepository.findById(seasonId);
        if(seasonResponse.isValid()){
            seasonRepository.deleteById(seasonId);
        }else{
            throw new ResourceNotFoundException("Season", "id", seasonId);
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
