package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    DistilleryRepository distilleryRepository;

    /**
     * Get /whiskies returning [] of all the whiskies
     * Get /whiskies?year=2018 return all the whiskies for a particular year
     * Get /whiskies?distilleryID=1&whiskyAge=15
     * Get /whiskies?region=Islay
     * Get /whiskies?nameContain=Glen
     */
    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name = "year",required = false) Integer year,
            @RequestParam(name = "distilleryID", required = false) Long distilleryID,
            @RequestParam(name = "whiskyAge", required = false) Integer whiskyAge,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "nameContain", required = false) String nameContain
    ){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }

        if(distilleryID != null && whiskyAge != null){
            Distillery distillery = distilleryRepository.getOne(distilleryID);
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery,whiskyAge), HttpStatus.OK);
        }

        if(region != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        if(nameContain != null){
            return new ResponseEntity<>(whiskyRepository.findByNameContaining(nameContain), HttpStatus.OK);
        }
        List<Whisky> foundWhiskies = whiskyRepository.findAll();
        return new ResponseEntity<List<Whisky>>(foundWhiskies, HttpStatus.OK);
    }

    /**
     * Get /whiskies/distilleries/age/{age} returning [] of all the whiskies from a particular distillery that's a specific age
     */
//    @GetMapping(value = "/whiskies/distilleries/age/{age}")
//    public ResponseEntity<List<Whisky>> findWhiskiesFromDistilleryAndAge(@PathVariable Integer age){
//        return whiskyRepository.findBy
//    }
}
