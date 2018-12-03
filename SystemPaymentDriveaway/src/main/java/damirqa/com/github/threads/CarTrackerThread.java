package damirqa.com.github.threads;

import damirqa.com.github.enums.PlaceStatus;
import damirqa.com.github.models.Car;
import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;

import static java.lang.Thread.sleep;

import javax.swing.JTextArea;

public class CarTrackerThread implements Runnable{
	
	private Place place = null;
	private Car car;
	private JTextArea log;
	
	public CarTrackerThread(JTextArea log) {
		this.log = log;
	}
	
		
	public void run() {
		while(true) {
			place = null;
			getCar();
									
			for (Place workPlace : Statistics.PLACES) {
				if (workPlace.getStatus() == PlaceStatus.WORK && workPlace.getQueue().size() < 3) {
					place = workPlace;
					place.getQueue().add(car);
					Statistics.QUEUE.remove(car);
					
					log.append(" Машина №" + car.getId() + " встала в очередь к терминалу №" + place.getId() + "\n");
					log.setCaretPosition(log.getText().length());
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
						
						log.append(" Трекер открыл терминал №" + place.getId() + ". Машина №" + car.getId() + " подъехала к этому терминалу\n");
						log.setCaretPosition(log.getText().length());
						break;
					}
				}
			}
						
			if (place == null) { 
				int id = 0;
				int min = Statistics.PLACES.get(0).getQueue().size();
				int max = Statistics.PLACES.get(0).getQueue().size();
				
				for (Place anyWorkPlace : Statistics.PLACES) {
					if (anyWorkPlace.getQueue().size() > max) {
						max = anyWorkPlace.getQueue().size();
					}
					else{
						id = anyWorkPlace.getId();
						min = anyWorkPlace.getQueue().size();
					}
				}
				
				place = Statistics.PLACES.get(id - 1);
				place.getQueue().add(car);
				Statistics.QUEUE.remove(car);
				
				log.append(" Машина №" + car.getId() + " встала в очередь к терминалу №" + place.getId() + ", так как там меньше всего машин стоят в очереди\n");
				log.setCaretPosition(log.getText().length());
			}
			
			try {
				sleep(1000);
				Statistics.SPENT += 1;
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}
	
	private void getCar() {
		if (Statistics.QUEUE.isEmpty()) {
			try {
				sleep(1000);
				Statistics.SPENT += 1;
			} catch (InterruptedException e) {
				e.getMessage();
			}
			getCar();
		}
		else {
			car = Statistics.QUEUE.get(0); 
		}
	}
}
