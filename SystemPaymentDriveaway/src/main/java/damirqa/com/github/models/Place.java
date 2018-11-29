package damirqa.com.github.models;

import damirqa.com.github.enums.PlaceStatus;

public class Place {
	
	private static int counter = 0;
	private int id;
	
	private Barrier barrier;
	private PaymentTerminal paymentTerminal;
	
	private PlaceStatus status;
	
	public Place() {
		counter = counter + 1;
		id = counter;
		
		barrier = new Barrier();
		paymentTerminal = new PaymentTerminal();
		
		status = PlaceStatus.DONTWORK;
	}
}
