package mg0523.test.toolrental.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import mg0523.toolrental.service.HolidayUtil;

public class HolidayUtilTest {
	
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
	
	@Test
	void testUtilIdentifiesWeekends() {
		LocalDate saturday = LocalDate.parse("05/27/23", format);
		LocalDate sunday = LocalDate.parse("05/28/23", format);
		LocalDate monday = LocalDate.parse("05/29/23", format);
		LocalDate tuesday = LocalDate.parse("05/30/23", format);
		assertTrue(HolidayUtil.isWeekend(saturday));
		assertTrue(HolidayUtil.isWeekend(sunday));
		assertFalse(HolidayUtil.isWeekend(monday));
		assertFalse(HolidayUtil.isWeekend(tuesday));
	}
	
	@Test
	void testUtilIdentifies4thOfJulyBusinessHoliday() {
		assertTrue(HolidayUtil.isJuly4th(LocalDate.parse("07/05/21", format)));
		assertFalse(HolidayUtil.isJuly4th(LocalDate.parse("07/03/23", format)));
		assertFalse(HolidayUtil.isJuly4th(LocalDate.parse("07/04/20", format)));
		assertTrue(HolidayUtil.isJuly4th(LocalDate.parse("07/04/19", format)));
	}
	
	@Test
	void testUtilRecognizedLaborDay() {
		assertTrue(HolidayUtil.isLaborDay(LocalDate.parse("09/04/23", format)));
		assertFalse(HolidayUtil.isLaborDay(LocalDate.parse("08/01/22", format)));
	}
	
	@Test
	void testIsHoliday() {
		assertTrue(HolidayUtil.isHoliday(LocalDate.parse("07/04/22", format)));
		assertTrue(HolidayUtil.isHoliday(LocalDate.parse("09/03/18", format)));
	}
}
