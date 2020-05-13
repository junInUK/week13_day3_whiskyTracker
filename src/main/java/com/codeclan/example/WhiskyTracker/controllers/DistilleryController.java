package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    /**
     * GET /distilleries returning [] of all the pirates
     * GET /distilleries?region=Highland
     * GET /distilleries?whiskyAge=12
     */
    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(
        @RequestParam(name = "region", required = false) String region,
        @RequestParam(name = "whiskyAge", required = false) Integer whiskyAge
    ){
        if(region != null){
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }

        if(whiskyAge != null){
            return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(whiskyAge), HttpStatus.OK);
        }

        List<Distillery> foundDistilleries = distilleryRepository.findAll();
        return new ResponseEntity<List<Distillery>>(foundDistilleries, HttpStatus.OK);

    }

}
