package mg0523.toolrental.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import mg0523.toolrental.datamodel.PricingData;
import mg0523.toolrental.datamodel.Reciept;

public class RentalService {
	
	/**
	 * Produces a receipt for a rental given the tool desired, the checkout date, the number of days and a discount amount.
	 * @param toolCode
	 * @param checkoutDate
	 * @param discountPercent
	 * @param days
	 * @return a receipt that could, in principal, be returned to a front-end client.
	 */
	public Reciept calculateTotals(String toolCode, LocalDate checkoutDate, int days, int discountPercent) {
		if (days < 1) {
			throw new RuntimeException("Tools must be rented for at least 1 day. Please check the rental duration.");
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new RuntimeException("Discount invalid. Verify the discount entered is between 0 and 100.");
		}
		var tool = ToolService.getToolByToolCode(toolCode);
		var price = Pricing.getPricingByToolType(tool.getToolType());
		int chargeDays = calculateChargeDays(checkoutDate, days, price);
		double basePrice = round(price.getDailyCharge() * (double) chargeDays);
		double discountAmount = round(basePrice * (double) discountPercent / 100.0);
		BigDecimal finalPrice = BigDecimal.valueOf(basePrice).subtract(BigDecimal.valueOf(discountAmount));
		return new Reciept(tool, price, days, checkoutDate, checkoutDate.plusDays(days), chargeDays, discountPercent, basePrice, discountAmount, finalPrice);
	}
	
	/**
	 * Produces a receipt for a rental given the tool code, checkout date as a string, the number of days, and a discount.
	 * @param toolCode
	 * @param checkoutDate
	 * @param days
	 * @param discountPercent
	 * @return
	 */
	public Reciept calculateTotals(String toolCode, String checkoutDate, int days, int discountPercent) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
		return calculateTotals(toolCode, LocalDate.parse(checkoutDate, format), days, discountPercent);
	}
	
	/**
	 * Take a receipt prints out the values with labels.
	 * @param receipt
	 */
	public void print(Reciept receipt) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
		System.out.println("Tool Code:           " + receipt.getTool().getToolCode());
		System.out.println("Tool Type:           " + receipt.getTool().getToolType());
		System.out.println("Tool Brand:          " + receipt.getTool().getBrand());
		System.out.println("Rental Days:         " + receipt.getRentalDays());
		System.out.println("Checkout Date:       " + receipt.getCheckoutDate().format(format));
		System.out.println("Due Date:            " + receipt.getDueDate().format(format));
		System.out.println("Daily Rental Charge: " + receipt.getPricing().getDailyCharge());
		System.out.println("Charge Days:         " + receipt.getChargeDays());
		System.out.println("Pre-Discount Charge: " + receipt.getPreDiscountCharge());
		System.out.println("Discount Amount      " + receipt.getDiscountAmount());
		System.out.println("Discount Percent     " + receipt.getDiscountPercent());
		System.out.println(" -------------------------------------- ");
		System.out.println("Final Charge         " + receipt.getFinalCharge());
	}
	
	/**
	 * Calculates the number of days that should count towards the final price.
	 * @param startDate
	 * @param days
	 * @param price
	 * @return the number of days should count towards the final prices.
	 */
	private int calculateChargeDays(LocalDate startDate, int days, PricingData price) {
		if (price.isHolidayCharge() && price.isWeekendCharge()) {
			return days;
		}
		else {
			int chargeDays = 0;
			for (int i = 0; i < days; i++) {
				LocalDate day = startDate.plusDays(i);
				if (!price.isWeekendCharge() && HolidayUtil.isWeekend(day)) {
					continue;
				}
				if (!price.isHolidayCharge() && HolidayUtil.isHoliday(day)) {
					continue;
				}
				chargeDays++;
			}
			return chargeDays;
		}
	}
	
	/**
	 * Rounds a value at two decimal places.
	 * 
	 * @param value
	 * @return rounded value
	 */
	private double round(double value) {
		return Math.round(value * 100) / 100.0;
	}
}
