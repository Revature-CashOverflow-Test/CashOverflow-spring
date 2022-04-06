package com.revature.model;

/**
 * Enum for notification messages
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
public enum NotiMessage {
	
	TEST_MSG("test noti");
	
	private String message;
	
	private NotiMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
