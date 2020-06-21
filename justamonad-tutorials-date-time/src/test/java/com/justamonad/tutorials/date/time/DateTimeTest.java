package com.justamonad.tutorials.date.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class DateTimeTest {

	@Test
	public void testLocalDateTime() {

		// Gets the timezone that you are in.
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		System.out.println(localDateTime);

		localDateTime.toString();// Prints 2020-06-17T14:25:41.776
		localDate.getDayOfMonth();// Prints 17
		localDate.getDayOfYear();// Prints 169
		localDate.getDayOfWeek();// Prints WEDNESDAY
		localDate.getYear(); // Prints 2020

		localDate.getMonth(); // JUNE
		LocalDate localDate1 = LocalDate.of(2020, Month.MAY, 12);
		System.out.println(localDate1);

	}

	@Test
	public void testZoneDateTime() {

		// Gets the timezone that you are in.
		ZonedDateTime.now(); // 2020-06-17T14:35:52.223-07:00[America/Los_Angeles]
		ZonedDateTime newZone = ZonedDateTime.parse("2020-08-20T12:30:56.223-07:00[America/Chicago]");
		System.out.println(newZone);

		ZoneId zoneId = ZoneId.of("America/Chicago");
		System.out.println(zoneId.getId());
		System.out.println(zoneId.getRules());
		System.out.println(ZoneId.getAvailableZoneIds().size());
		System.out.println((ZonedDateTime.now().getZone().getRules()));
		System.out.println(ZoneId.of("Europe/London").getRules());
	}

	@Test
	public void allZoneOffset() {
		
		ZoneId.getAvailableZoneIds().stream().sorted().map(zone -> ZoneId.of(zone))
				.forEach(z -> System.out.println(z + " " + z.getRules()));

	}

}
