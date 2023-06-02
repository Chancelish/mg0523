package mg0523.toolrental;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.IOException;

import mg0523.toolrental.service.RentalService;

public class ToolRental {
	
	public static void main(String[] args) throws IOException {
		var rentalService = new RentalService();
		var format = DateTimeFormatter.ofPattern("MM/dd/yy");
		BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while (true) {
			System.out.println("Rent out a tool! (Format: <tool code> <start date> <days> <discount>");
			input = systemIn.readLine();
			var inputArgs = input.split(" ");
			if (input.equals("end")) {
				break;
			}
			try {
				var receipt = rentalService.calculateTotals(inputArgs[0],
						LocalDate.parse(inputArgs[1], format),
						Integer.parseInt(inputArgs[2]),
						Integer.parseInt(inputArgs[3]));
				rentalService.print(receipt);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
		}
	}
}
