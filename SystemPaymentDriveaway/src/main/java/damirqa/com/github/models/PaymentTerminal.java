package damirqa.com.github.models;

import damirqa.com.github.enums.PaymentTerminalStatus;

public class PaymentTerminal {
	
	private static int counter = 0;
	private int id;
	
	private PaymentTerminalStatus status;
	
	public PaymentTerminal() {
		counter = counter + 1;
		id = counter;
		
		status = PaymentTerminalStatus.DONTWORK;
	}
	
	public void setStatus() {
		status = PaymentTerminalStatus.WORK;
	}
}
