package mg0523.test.toolrental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mg0523.toolrental.service.RentalService;

/**
 * This class contains all the test cases for the final submission.
 * 
 */
public class RentalServiceTest {
	
	private RentalService rentalService;
	
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
	
	@BeforeEach
	void setUp() {
		rentalService = new RentalService();
	}
	
	@AfterEach
	void afterEach() {
		System.out.println();
		System.out.println();
	}

	@Test
	void TestCase1() {
		System.out.println("Test Case 1");
		RuntimeException e = assertThrows(RuntimeException.class, () -> rentalService.calculateTotals("JAKR", "09/03/15", 5, 101));
		assertEquals("Discount invalid. Verify the discount entered is between 0 and 100.", e.getMessage());
		System.out.println(e.getMessage());
	}
	
	@Test
	void testCase2() {
		System.out.println("Test Case 2");
		var receipt = rentalService.calculateTotals("LADW", "07/02/20", 3, 10);
		assertEquals(2, receipt.getChargeDays());
		assertEquals("07/05/20", receipt.getDueDate().format(format));
		assertEquals(3.98, receipt.getPreDiscountCharge());
		assertEquals(0.40, receipt.getDiscountAmount());
		assertEquals(BigDecimal.valueOf(3.58), receipt.getFinalCharge());
		rentalService.print(receipt);
	}
	
	@Test
	void testCase3() {
		System.out.println("Test Case 3");
		var receipt = rentalService.calculateTotals("CHNS", "07/02/15", 5, 25);
		assertEquals(3, receipt.getChargeDays());
		assertEquals("07/07/15", receipt.getDueDate().format(format));
		assertEquals(4.47, receipt.getPreDiscountCharge());
		assertEquals(1.12, receipt.getDiscountAmount());
		assertEquals(BigDecimal.valueOf(3.35), receipt.getFinalCharge());
		rentalService.print(receipt);
	}
	
	@Test
	void testCase4() {
		System.out.println("Test Case 4");
		var receipt = rentalService.calculateTotals("JAKD", "09/03/15", 6, 0);
		assertEquals(3, receipt.getChargeDays());
		assertEquals("09/09/15", receipt.getDueDate().format(format));
		assertEquals(8.97, receipt.getPreDiscountCharge());
		assertEquals(0, receipt.getDiscountAmount());
		assertEquals(BigDecimal.valueOf(8.97), receipt.getFinalCharge());
		rentalService.print(receipt);
	}
	
	@Test
	void testCase5() {
		System.out.println("Test Case 5");
		var receipt = rentalService.calculateTotals("JAKR", "07/02/15", 9, 0);
		assertEquals(6, receipt.getChargeDays());
		assertEquals("07/11/15", receipt.getDueDate().format(format));
		assertEquals(17.94, receipt.getPreDiscountCharge());
		assertEquals(0, receipt.getDiscountAmount());
		assertEquals(BigDecimal.valueOf(17.94), receipt.getFinalCharge());
		rentalService.print(receipt);
	}
	
	@Test
	void testCase6() {
		System.out.println("Test Case 6");
		var receipt = rentalService.calculateTotals("JAKR", "07/02/20", 4, 50);
		assertEquals(1, receipt.getChargeDays());
		assertEquals("07/06/20", receipt.getDueDate().format(format));
		assertEquals(2.99, receipt.getPreDiscountCharge());
		assertEquals(1.50, receipt.getDiscountAmount());
		assertEquals(BigDecimal.valueOf(1.49), receipt.getFinalCharge());
		rentalService.print(receipt);
	}
}
