package mg0523.toolrental.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A data model representing a tool for rent.
 *
 */
@Getter
@AllArgsConstructor
public class Tool {
	private String toolCode;
	private String toolType;
	private String brand;
}
