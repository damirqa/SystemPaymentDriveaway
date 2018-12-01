package damirqa.com.github.models;

import damirqa.com.github.enums.BarrierStatus;

public class Barrier {
	
	private static int counter = 0;
	private int id;
	
	private BarrierStatus status;
	
	public Barrier() {
		counter = counter + 1;
		id = counter;
		
		status = BarrierStatus.DONTWORK;
	}
	
	public void setStatus() {
		status = BarrierStatus.WORK;
	}
}
