package mg0523.test.toolrental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mg0523.toolrental.service.ToolService;

public class ToolServiceTest {

	void toolServiceShouldProvideClearErrorForPriceNotFound() {
		Exception e = assertThrows(RuntimeException.class, () -> ToolService.getToolByToolCode("DRLW"));
		assertEquals("DRLW is not registered to this store.", e.getMessage());
	}
	
	@Test
	void testRetrievingTool() {
		var tool = ToolService.getToolByToolCode("CHNS");
		assertEquals("Chainsaw", tool.getToolType());
		assertEquals("Stihl", tool.getBrand());
	}
}
