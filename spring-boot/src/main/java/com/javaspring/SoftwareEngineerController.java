package com.javaspring;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineeriService softwareEngineeriService;

    public SoftwareEngineerController(SoftwareEngineeriService softwareEngineeriService) {
        this.softwareEngineeriService = softwareEngineeriService;
    }

    @GetMapping("/")
    public List<SoftwareEngineer> getEngineers(){
        return softwareEngineeriService.getAllSoftwareEngineers();
    }


    @PostMapping
    public void addNewSoftwareEngineer
            (@RequestBody SoftwareEngineer softwareEngineer){
            softwareEngineeriService.insertSoftwareEngineer(softwareEngineer);
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(
            @PathVariable Integer id
    ){
        return softwareEngineeriService.getSoftwareEngineerById(id);
    }


    @DeleteMapping("{id}")
    public void deleteEngineerById(
            @PathVariable Integer id
    ){
         softwareEngineeriService.deleteSoftwareEngineerById(id);
    }
}
