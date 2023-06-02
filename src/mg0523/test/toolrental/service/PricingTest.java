package mg0523.test.toolrental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mg0523.toolrental.service.Pricing;

public class PricingTest {

	@Test
	void pricingShouldProvideClearErrorForPriceNotFound() {
		Exception e = assertThrows(RuntimeException.class, () -> Pricing.getPricingByToolType("Drill"));
		assertEquals("404: No data available for tool of type Drill.", e.getMessage());
	}
	
	@Test
	void testRetrievingPrice() {
		var price = Pricing.getPricingByToolType("Ladder");
		assertEquals(1.99, price.getDailyCharge());
		assertTrue(price.isWeekendCharge());
		assertFalse(price.isHolidayCharge());
	}
}
