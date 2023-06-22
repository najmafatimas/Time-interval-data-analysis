package com.example.demoPro.entities;

import java.util.List;

public class FileProcessingRequest {


	private List<Integer> deviceIds;
    private long startTime;
    private long endTime;
	public List<Integer> getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(List<Integer> deviceIds) {
		this.deviceIds = deviceIds;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
