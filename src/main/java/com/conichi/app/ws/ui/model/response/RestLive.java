package com.conichi.app.ws.ui.model.response;

import java.util.ArrayList;
import java.util.HashMap;

public class RestLive {
	private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String source;
    private HashMap<String, Double> quotes;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public HashMap<String, Double> getQuotes() {
		return quotes;
	}
	public void setQuotes(HashMap<String, Double> quotes) {
		this.quotes = quotes;
	}
	   
    
}
