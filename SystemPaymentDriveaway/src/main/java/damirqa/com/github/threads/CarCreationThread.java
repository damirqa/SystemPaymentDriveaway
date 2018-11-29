package damirqa.com.github.threads;

import java.util.Random;

import damirqa.com.github.models.Car;
import damirqa.com.github.storage.Statistics;

import static java.lang.Thread.sleep;

public class CarCreationThread implements Runnable{

	public void run() {
		while(true) {
			Statistics.QUEUE.add(new Car());
			int timeBetweenCar = new Random().nextInt(90000 - 10000) + 10000;
			try {
				sleep(timeBetweenCar);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}
}
