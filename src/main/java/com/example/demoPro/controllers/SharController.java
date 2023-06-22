package com.example.demoPro.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoPro.entities.FileProcessingRequest;
import com.example.demoPro.services.SharService;

@RestController
public class SharController {

//	@PostConstruct
//	public void load()
//	{
//		long timestamp = 1676048647;
//
//        // Convert the timestamp to Instant
//        Instant instant = Instant.ofEpochMilli(timestamp);
//
//        // Convert Instant to LocalDateTime in UTC
//        LocalDateTime utcDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
//
//        // Format the UTC datetime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = formatter.format(utcDateTime);
//
//        System.out.println("UTC Date and Time: " + formattedDateTime);
//	}
	@Autowired
	private SharService sharService;

	@PostMapping(value=
			"/processData")
    public ResponseEntity<?> processData(@RequestBody FileProcessingRequest fr) {
		 Map<Integer, Integer> deviceMessageCounts = sharService.processData(fr);
	        if (deviceMessageCounts != null) {
	            return ResponseEntity.ok(deviceMessageCounts);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
    }
}
