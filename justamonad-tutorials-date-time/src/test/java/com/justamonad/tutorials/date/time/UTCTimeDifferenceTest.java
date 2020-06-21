package com.justamonad.tutorials.date.time;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UTCTimeDifferenceTest {

	@Test
	public void printAllTimeZoneDifferenceUsingStream() throws Exception {
		Map<String, String> shortIds = new TreeMap<>(ZoneId.SHORT_IDS);
		List<TimeOffset> timeOffsets = 
				shortIds
				.entrySet()
				.stream()
				.map(entry -> ZoneId.of(entry.getValue()))
				.map(zoneId -> createTimeOffset(zoneId))
				.collect(Collectors.toList());

		String json = new ObjectMapper().writeValueAsString(timeOffsets);
		System.out.println(json);
	}

	@Test
	public void printAllTimeZoneDifference() throws Throwable {
		Map<String, String> shortIds = new TreeMap<>(ZoneId.SHORT_IDS);
		List<TimeOffset> timeOffsets = new ArrayList<TimeOffset>();

		for (Map.Entry<String, String> entry : shortIds.entrySet()) {
			ZoneId zoneId = ZoneId.of(entry.getValue());
			TimeOffset timeOffset = createTimeOffset(zoneId); 
			timeOffsets.add(timeOffset);
		}
		String json = new ObjectMapper().writeValueAsString(timeOffsets);
		System.out.println(json);
	}

	@Test
	public void getAllAvailableTimeZones() throws Exception {
		List<TimeOffset> timeOffsets = 
				ZoneId.getAvailableZoneIds()
					.stream()
					.sorted()
					.map(zone -> ZoneId.of(zone))
					.map(zoneId -> createTimeOffset(zoneId))
					.collect(Collectors.toList());
//		String json = new ObjectMapper().writeValueAsString(timeOffsets);
//		System.out.println(json);
		System.out.println(timeOffsets.size());
	}

	/**
	 * zoneId.getRules().toString() looks like this so we need to process it
	 * ZoneRules[currentStandardOffset=+09:30]
	 */
	private TimeOffset createTimeOffset(ZoneId zoneId) {
		TimeOffset timeOffset = new TimeOffset();
		timeOffset.setZoneId(zoneId.getId());
		timeOffset.setCurrentStandardOffset(zoneId.getRules().toString().split("=")[1].replace("]", ""));
		return timeOffset;
	}

}
