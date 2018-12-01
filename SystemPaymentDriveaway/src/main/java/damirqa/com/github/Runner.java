package damirqa.com.github;

import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;
import damirqa.com.github.threads.CarCreationThread;
import damirqa.com.github.threads.CarTrackerThread;
import damirqa.com.github.threads.PlaceThread;

public class Runner {

	public static void main(String[] args) {
	
		for (int i = 0; i < 5; i++) {
			Place place = new Place();
			Statistics.PLACES.add(place);
			new Thread(new PlaceThread(Statistics.PLACES.get(i))).start();
		}
		
		Statistics.PLACES.get(0).setStatus();
		
		Thread carCreation = new Thread(new CarCreationThread());
		carCreation.start();
		
		Thread carTracker = new Thread(new CarTrackerThread());
		carTracker.start();
	}
}
