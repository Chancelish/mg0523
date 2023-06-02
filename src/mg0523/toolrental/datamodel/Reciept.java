package mg0523.toolrental.datamodel;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A data model representing the receipt from a point of sale for a rental company.
 *
 */
@Getter
@AllArgsConstructor
public class Reciept {
	private Tool tool;
	private PricingData pricing;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private int chargeDays;
	private double discountPercent;
	private double preDiscountCharge;
	private double discountAmount;
	private BigDecimal finalCharge;
}
