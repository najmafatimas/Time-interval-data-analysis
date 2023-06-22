package com.example.demoPro.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoPro.entities.FileProcessingRequest;
import com.example.demoPro.entities.Message;
import com.example.demoPro.services.SharService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SharController {

	@Autowired
	private SharService sharService;

	@PostMapping(value=
			"/processData",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> processData(@RequestPart MultipartFile file,@RequestPart FileProcessingRequest fr) {
		 Map<Integer, Integer> deviceMessageCounts = sharService.processData(file, fr);
	        if (deviceMessageCounts != null) {
	            return ResponseEntity.ok(deviceMessageCounts);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
    }
}
