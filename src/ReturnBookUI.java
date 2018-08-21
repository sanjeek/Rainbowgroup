import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl control;
	private static Scanner scanner = new Scanner( System.in ); // change the scanner input.
	private UI_STATE state;

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		Scanner scanner = new Scanner( System.in ); // change the scanner input..
		
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}


	public void run() {		
		    System.out.println("Return Book Use Case UI\n"); // Change the output to  System.out.println.
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
			break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid bookId"); // Change the output to  System.out.println.
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				System.out.println("Return processing complete"); // Change the output to  System.out.println.
				return;
			
			default:
			System.out.println("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}