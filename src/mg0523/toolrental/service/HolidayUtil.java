package mg0523.toolrental.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * A utility containing methods to determine with a day is on a weekend or holiday.
 *
 */
public final class HolidayUtil {
	private HolidayUtil() {	}
	
	/**
	 * Checks whether a date on a weekend.
	 * @param date
	 * @return true if the date is a Saturday or Sunday.
	 */
	public static boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}
	/**
	 * Checks whether a given date is a business holiday.
	 * @param date
	 * @return true if the date is a business holiday.
	 */
	public static boolean isHoliday(LocalDate date) {
		return isJuly4th(date) || isLaborDay(date);
	}
	
	/**
	 * Checks whether a given date is the fourth of July from the point of view of a business.
	 * Should the 4th fall on a weekend, it is instead observed on the closest weekday.
	 * @param date
	 * @return true if the date is the observed date for the 4th of July.
	 */
	public static boolean isJuly4th(LocalDate date) {
		if (date.getMonth() == Month.JULY) {
			if (date.getDayOfWeek() == DayOfWeek.FRIDAY ) {
				return date.getDayOfMonth() == 3 || date.getDayOfMonth() == 4;
			}
			else if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
				return date.getDayOfMonth() == 5 || date.getDayOfMonth() == 4;
			}
			else if (!isWeekend(date)){
				return date.getDayOfMonth() == 4;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether a given date 
	 * @param date
	 * @return true if the date is labor day.
	 */
	public static boolean isLaborDay(LocalDate date) {
		return date.getMonth() == Month.SEPTEMBER
				&& date.getDayOfWeek() == DayOfWeek.MONDAY
				&& date.getDayOfMonth() <= 7;
	}
}
