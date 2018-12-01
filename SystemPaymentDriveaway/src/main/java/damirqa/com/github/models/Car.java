package damirqa.com.github.models;

public class Car {

	private static int counter = 0;
	private int id;
	
	public Car() {
		counter = counter + 1;
		id = counter;
	}
	
	public int getId() {
		return id;
	}
}
