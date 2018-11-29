package damirqa.com.github;

import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;

public class Runner {

	public static void main(String[] args) {
	
		for (int i = 0; i < 5; i++) {
			Statistics.PLACES.add(new Place());
		}

	}

}
