package mg0523.toolrental.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A data model representing a pricing strategy for a rental.
 * 
 */
@Getter
@AllArgsConstructor
public class PricingData {
	private double dailyCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
}