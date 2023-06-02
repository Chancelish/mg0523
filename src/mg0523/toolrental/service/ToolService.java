package mg0523.toolrental.service;

import java.util.HashMap;
import java.util.Map;

import mg0523.toolrental.datamodel.Tool;

/**
 * Representative a class that would load tool data.
 * For simplicity, data is defined in line as static constants.
 *
 */
public final class ToolService {
	private static final Map<String, Tool> TOOLS = new HashMap<>();
	
	static {
		TOOLS.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
		TOOLS.put("LADW", new Tool("LADW", "Ladder", "Werner"));
		TOOLS.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
		TOOLS.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid"));
	}
	
	private ToolService() { }
	
	/**
	 * Looks up and returns a tool given an input tool code.
	 * @param toolCode
	 * @return the tool corresponding to the toolCode.
	 */
	public static Tool getToolByToolCode(String toolCode) {
		if (!TOOLS.containsKey(toolCode)) {
			throw new RuntimeException(toolCode + " is not registered to this store.");
		}
		return TOOLS.get(toolCode);
	}
}
