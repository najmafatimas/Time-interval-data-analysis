package com.example.demoPro.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoPro.entities.FileProcessingRequest;
import com.example.demoPro.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SharService {
	public Map<Integer, Integer> processData(MultipartFile file, FileProcessingRequest fr) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            System.out.println(lines.size());
            Map<Integer, Integer> deviceMessageCounts = new HashMap<>();
            for (String line1 : lines) {
                System.out.println("hello");
                if (!line1.isEmpty()) {
                    Message message = parseJsonMessage(line1);
                    if (message != null && fr.getDeviceIds().contains(message.getDeviceId())) {
                        long msgTime = message.getMsgTime();
                        if (msgTime >= fr.getStartTime() && msgTime <= fr.getStartTime()) {
                            // Increment message count for the device
                            deviceMessageCounts.put(message.getDeviceId(),
                                    deviceMessageCounts.getOrDefault(message.getDeviceId(), 0) + 1);
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
