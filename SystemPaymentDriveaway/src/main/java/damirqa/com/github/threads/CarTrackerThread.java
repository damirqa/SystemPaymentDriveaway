package damirqa.com.github.threads;

import damirqa.com.github.enums.PlaceStatus;
import damirqa.com.github.models.Car;
import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;

import static java.lang.Thread.sleep;

public class CarTrackerThread implements Runnable{
	
	Place place = null;
	Car car;
	
		
	public void run() {
		while(true) {
			
			place = null;
			
			getCar();
			
			//System.out.println("Машина №" + car.getId() + " проехала через трекер\n");
			
			for (Place workPlace : Statistics.PLACES) {
				if (workPlace.getStatus() == PlaceStatus.WORK && workPlace.getQueue().size() < 3) {
					place = workPlace;
					place.getQueue().add(car);
					Statistics.QUEUE.remove(car);
					
					System.out.println("Машина №" + car.getId() + " встала в очередь рабочего терминала №" + place.getId() + "\n");
					break;
				}
			}
						
			if (place == null) {
				for (Place dontWorkPlace : Statistics.PLACES) {
					if (dontWorkPlace.getStatus() == PlaceStatus.DONTWORK) {
						place = dontWorkPlace;
						place.setStatus();
						place.getQueue().add(car);
						Statistics.QUEUE.remove(car);
						System.out.println("Трекер открыл терминал №" + place.getId() + ". Машина №" + car.getId() + " подъехала к этому терминалу\n");
						break;
					}
				}
			}
			
			
			
			if (place == null) { 
				//System.out.println("Все терминалы включены");
				int id = 0;
				int min = Statistics.PLACES.get(0).getQueue().size();
				int max = Statistics.PLACES.get(0).getQueue().size();
				
				for (Place anyPlace : Statistics.PLACES) {
					if (anyPlace.getQueue().size() > max) {
						max = anyPlace.getQueue().size();
					}
					else {
						id = anyPlace.getId();
						min = anyPlace.getQueue().size();
					}
				}
				
				place = Statistics.PLACES.get(id - 1);
				place.getQueue().add(car);
				Statistics.QUEUE.remove(car);
				
				System.out.println(" Машина №" + car.getId() + " встала в очередь к терминалу №" + place.getId() + ", так как там меньше всего машин стоят в очереди\n");
				
			}
			
		}
	}
	
	private void getCar() {
		if (Statistics.QUEUE.isEmpty()) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			getCar();
		}
		else {
			car = Statistics.QUEUE.get(0); 
		}
	}
	
	private void getWorkingPlaces() {
		for (Place place : Statistics.PLACES) {
			if (place.getStatus() == PlaceStatus.WORK) {
				Statistics.WORKINGPLACES.add(place);
			}
		}
	}
	
	private Place getWorkingPlace() {
		for (Place place : Statistics.WORKINGPLACES) {
			if(place.getQueue().size() < 3) {
				return place;
			} 
		}
		return null;
	}
	
//	private Place getDontWorkingPlace() {
//		for (Place place : Statistics.PLACES) {
//			
//		}
//	}
}
