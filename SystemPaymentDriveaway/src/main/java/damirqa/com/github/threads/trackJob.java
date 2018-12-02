package damirqa.com.github.threads;

import damirqa.com.github.enums.PlaceStatus;
import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Statistics;

import static java.lang.Thread.sleep;

import javax.swing.JTextField;

public class trackJob implements Runnable{

	private JTextField field;
	
	public trackJob(JTextField field) {
		this.field = field;
	}
	
	public void run() {
		while(true) {
			for(Place place : Statistics.PLACES) {
				if(place.getStatus() == PlaceStatus.WORK) {
					Statistics.SPENT += 1;
				}
			}
			field.setText(Statistics.SPENT+"");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}

}
