package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	private DistilleryRepository distilleryRepository;
	@Autowired
	private WhiskyRepository whiskyRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskiesByDistilleryAndAge(){
		Distillery distillery = distilleryRepository.getOne(1L);
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryAndAge(distillery,15);
		System.out.println(foundWhiskies.get(0).getName());
	}

	@Test
	public void canFindWhiskiesByDistilleryRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion("Islay");
		System.out.println(foundWhiskies.size());
	}

	@Test
	public void canFindDistilleriesByWhiskiesAge(){
		List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesAge(10);
		System.out.println(foundDistilleries.size());
	}

	@Test
	public void canFindWhiskiesByNameContaining(){
		List<Whisky> foundWhiskies = whiskyRepository.findByNameContaining("Glen");
		System.out.println(foundWhiskies.size());
	}

//	@Test
//	public void canFindDistilleriesByWhiskiesNameBeginWith(){
//		List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesNameStartWith("The");
//		System.out.println(foundDistilleries.size());
//	}
}
