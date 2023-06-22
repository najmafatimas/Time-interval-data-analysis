package com.example.demoPro.entities;

public class Message {

	private int deviceId;
    private long msgTime;
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public long getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(long msgTime) {
		this.msgTime = msgTime;
	}
	@Override
	public String toString() {
		return "Message [deviceId=" + deviceId + ", msgTime=" + msgTime + "]";
	}

}
