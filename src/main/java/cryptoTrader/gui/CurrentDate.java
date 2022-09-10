/*
 * Class Description: This class provides the current date
 */

package cryptoTrader.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.text.SimpleDateFormat;


public class CurrentDate {
	
	private LocalDate date = LocalDate.now();
	private String formatDate;
	
	/*
	 * This method provides the date with the month in letters
	 * @return the date
	 */
	public String returnCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-uuuu");
		formatDate = date.format(formatter);
		return formatDate;
	}
	
	/*
	 * This method provides the date with the month in numbers
	 * @return the date
	 */
	public String returnNumberDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		formatDate = date.format(formatter);
		return formatDate;
	}
	

}
