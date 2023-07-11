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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demoPro.entities.FileProcessingRequest;
import com.example.demoPro.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SharService {
	
	@Value("${file.directory}")
	String fileDire;
	public Map<Integer, Integer> processData(FileProcessingRequest fr) {
        try {
        	System.out.println(fileDire);
        	List<Path> files = Files.walk(Paths.get(fileDire))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        	System.out.println(files);
        	Map<Integer, Integer> deviceMessageCounts = new HashMap<>();
        	for(Path file:files)
        	{
        		 List<String> lines = Files.readAllLines(file);
                 for (String line : lines) {
                	 if(!line.isEmpty())
                	 {
                     Message message = parseJsonMessage(line);
                     System.out.println(message);
                     if (message != null && fr.getDeviceIds().contains(message.getDeviceId())) {
                         long msgTime = message.getMsgTime();
                         if (msgTime >= fr.getStartTime() && msgTime <= fr.getEndTime()) {
                             // Increment message count for the device
                             deviceMessageCounts.put(message.getDeviceId(),
                                     deviceMessageCounts.getOrDefault(message.getDeviceId(), 0) + 1);
                         }
                         else if(deviceMessageCounts==null && deviceMessageCounts.get(message.getDeviceId())==0)
                         {
                        	 
                        	 deviceMessageCounts.put(message.getDeviceId(),0);
                         }
                        
                       
                    
                     }
                 }
        	}
        	}
//          for(Integer i:fr.getDeviceIds())
//          {
//        	  
//        	  if(!deviceMessageCounts.containsKey(i))
//        	  {
//        		  deviceMessageCounts.put(i,0);
//        	  }
//        	  
//          }
        	for (Integer deviceId : fr.getDeviceIds()) {
                deviceMessageCounts.putIfAbsent(deviceId, 0);
            }
       return deviceMessageCounts;
        
        }
        	catch (Exception e) {
            e.printStackTrace();
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
