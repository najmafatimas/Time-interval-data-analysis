package com.example.demoPro.entities;

import java.util.List;

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
	private List<MessageData> data;
	public List<MessageData> getData() {
		return data;
	}
	public void setData(List<MessageData> data) {
		this.data = data;
	}
	private long msgId;
	public long getMsgId() {

		return msgId;

		}

		public void setMsgId(long msgId) {

		this.msgId = msgId;

		}

		

		public int getInterval() {

		return interval;

		}

		public void setInterval(int interval) {

		this.interval = interval;

		}

		public String getTransactionId() {

		return transactionId;

		}

		public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;

		}

		public String getCustomer() {

		return customer;

		}

		public void setCustomer(String customer) {

		this.customer = customer;

		}

		public String getSiteId() {

		return siteId;

		}

		public void setSiteId(String siteId) {

		this.siteId = siteId;

		}

		public String getSensorType() {

		return sensorType;

		}

		public void setSensorType(String sensorType) {

		this.sensorType = sensorType;

		}

		public String getDataType() {

		return dataType;

		}

		public void setDataType(String dataType) {

		this.dataType = dataType;

		}

		private int interval;

		

		private String transactionId;

		private String customer;

		private String siteId;

		private String sensorType;

		private String dataType;
	

}
