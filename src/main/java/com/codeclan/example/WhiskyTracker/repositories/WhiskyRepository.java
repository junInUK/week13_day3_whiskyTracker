package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByYear(int year);

    List<Whisky> findByDistilleryAndAge(Distillery distillery, int age);

    List<Whisky> findByDistilleryRegion(String region);

    List<Whisky> findByNameContaining(String contain);
}
