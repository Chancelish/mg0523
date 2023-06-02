package mg0523.toolrental.service;

import java.util.HashMap;
import java.util.Map;

import mg0523.toolrental.datamodel.PricingData;

/**
 * Represents a class that would load pricing for a given tool type.
 * For simplicity, the pricing is an in line constant rather than loaded externally. 
 *
 */
public final class Pricing {
	
	private static final Map<String, PricingData> PRICING_BY_TOOL = new HashMap<>();
	
	static {
		PRICING_BY_TOOL.put("Chainsaw", new PricingData(1.49, false, true));
		PRICING_BY_TOOL.put("Jackhammer", new PricingData(2.99, false, false));
		PRICING_BY_TOOL.put("Ladder", new PricingData(1.99, true, false));
	}
	
	private Pricing() { }
	
	public static PricingData getPricingByToolType(String toolType) {
		if (!Pricing.PRICING_BY_TOOL.containsKey(toolType)) {
			throw new RuntimeException("404: No data available for tool of type " +  toolType + ".");
		}
		return Pricing.PRICING_BY_TOOL.get(toolType);
	}
}
	

