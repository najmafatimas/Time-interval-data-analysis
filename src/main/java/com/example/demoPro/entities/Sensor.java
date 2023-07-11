package com.example.demoPro.entities;

import java.util.List;

public class Sensor {

	private int sensorId;

	public int getSensorId() {

	return sensorId;

	}

	public void setSensorId(int sensorId) {

	this.sensorId = sensorId;

	}

	public List<Integer> getVal() {

	return val;

	}

	public void setVal(List<Integer> val) {

	this.val = val;

	}

	private List<Integer> val;
}
