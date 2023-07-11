package com.example.demoPro.entities;

import java.util.List;

public class MessageData {

	private int channelId;

	public int getChannelId() {

	return channelId;

	}

	public void setChannelId(int channelId) {

	this.channelId = channelId;

	}

	public int getMeasureId() {

	return measureId;

	}

	public void setMeasureId(int measureId) {

	this.measureId = measureId;

	}

	public long getTime() {

	return time;

	}

	public void setTime(long time) {

	this.time = time;

	}

	public int getStatus() {

	return status;

	}

	public void setStatus(int status) {

	this.status = status;

	}

	public long getSensorSn() {

	return sensorSn;

	}

	public void setSensorSn(long sensorSn) {

	this.sensorSn = sensorSn;

	}

	public List<Sensor> getSensor() {

	return sensor;

	}

	public void setSensor(List<Sensor> sensor) {

	this.sensor = sensor;

	}

	private int measureId;

	private long time;

	private int status;

	private long sensorSn;

	private List<Sensor> sensor;
}
