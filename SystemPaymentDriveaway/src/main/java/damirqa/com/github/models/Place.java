package damirqa.com.github.models;

import java.util.ArrayList;

import damirqa.com.github.enums.PlaceStatus;

public class Place {
	
	private static int counter = 0;
	private int id;
	
	private Barrier barrier;
	private PaymentTerminal paymentTerminal;
	
	private ArrayList<Car> queue;
	private PlaceStatus status;
	
	public Place() {
		counter = counter + 1;
		id = counter;
		
		barrier = new Barrier();
		paymentTerminal = new PaymentTerminal();
		
		queue = new ArrayList<Car>();
		status = PlaceStatus.DONTWORK;
	}
	
	public void setStatus() {
		barrier.setStatus();
		paymentTerminal.setStatus();
		status = PlaceStatus.WORK;
	}
	
	public PlaceStatus getStatus() {
		return status;
	}
	
	public ArrayList<Car> getQueue(){
		return queue;
	}
	
	public void addCarToQueue(Car car) {
		queue.add(car);
	}
	
	public int getId() {
		return id;
	}
}
