package com.javaspring;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineeriService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineeriService
            (SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer
            (SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException(id+"Not Found"));
    }

    public void deleteSoftwareEngineerById(Integer id) {
         softwareEngineerRepository.deleteById(id);
    }
}
