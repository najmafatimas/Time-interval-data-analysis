package com.example.demoPro.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demoPro.entities.FileProcessingRequest;
import com.example.demoPro.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SharService {
	public Map<Integer, Integer> processData(FileProcessingRequest fr) {
        try {
        	List<Path> files = Files.walk(Paths.get("C:/inputs"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        	System.out.println(files);
        	Map<Integer, Integer> deviceMessageCounts = new HashMap<>();
        	for(Path file:files)
        	{
        		 List<String> lines = Files.readAllLines(file);
                 for (String line : lines) {
                     // Parse the JSON message
                	 if(!line.isEmpty())
                	 {
                     Message message = parseJsonMessage(line);
                     if (message != null && fr.getDeviceIds().contains(message.getDeviceId())) {
                         long msgTime = message.getMsgTime();
                         if (msgTime >= fr.getStartTime() && msgTime <= fr.getEndTime()) {
                             // Increment message count for the device
                             deviceMessageCounts.put(message.getDeviceId(),
                                     deviceMessageCounts.getOrDefault(message.getDeviceId(), 0) + 1);
                         }
                     }
                     }
                 }
        	}
           
            return deviceMessageCounts;
        } catch (Exception e) {
            e.printStackTrace();
            // Replace with appropriate error handling mechanism, such as throwing a custom exception or returning an error response.
            return null;
        }
    }

    private Message parseJsonMessage(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
