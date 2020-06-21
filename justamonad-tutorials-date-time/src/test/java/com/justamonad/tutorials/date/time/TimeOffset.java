package com.justamonad.tutorials.date.time;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "zone_id", "current_standard_offset" })
public class TimeOffset {

	@JsonProperty("zone_id")
	private String zoneId;
	
	@JsonProperty("current_standard_offset")
	private String currentStandardOffset;

	@JsonProperty("zone_id")
	public String getZoneId() {
		return zoneId;
	}

	@JsonProperty("zone_id")
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public TimeOffset withZoneId(String zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	@JsonProperty("current_standard_offset")
	public String getCurrentStandardOffset() {
		return currentStandardOffset;
	}

	@JsonProperty("current_standard_offset")
	public void setCurrentStandardOffset(String currentStandardOffset) {
		this.currentStandardOffset = currentStandardOffset;
	}

	public TimeOffset withCurrentStandardOffset(String currentStandardOffset) {
		this.currentStandardOffset = currentStandardOffset;
		return this;
	}

}
