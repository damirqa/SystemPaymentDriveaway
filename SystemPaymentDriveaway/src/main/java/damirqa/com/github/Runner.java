package damirqa.com.github;

import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;
import damirqa.com.github.threads.CarCreationThread;
import damirqa.com.github.threads.CarTrackerThread;

public class Runner {

	public static void main(String[] args) {
	
		for (int i = 0; i < 5; i++) {
			Statistics.PLACES.add(new Place());
		}
		
		Statistics.PLACES.get(0).setStatus();
		
		Thread carCreation = new Thread(new CarCreationThread());
		carCreation.start();
		
		Thread carTracker = new Thread(new CarTrackerThread());
		carTracker.start();
	}
}
