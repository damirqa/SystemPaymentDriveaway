package damirqa.com.github.threads;

import damirqa.com.github.enums.BarrierStatus;
import damirqa.com.github.enums.PaymentTerminalStatus;
import damirqa.com.github.enums.PlaceStatus;
import damirqa.com.github.models.Barrier;
import damirqa.com.github.models.Car;
import damirqa.com.github.models.PaymentTerminal;
import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Budgeting;

import static java.lang.Thread.sleep;

import java.util.Random;

import javax.swing.JTextArea;

public class PlaceThread implements Runnable{

	private Place place;
	private JTextArea log;
	
	public PlaceThread(Place place, JTextArea log) {
		this.place = place;
		this.log = log;
	}
	
	public void run() {
		while(true) {
			if (place.getStatus() == PlaceStatus.WORK && !place.getQueue().isEmpty()) {
								
				int conditionTerminal = (int) (Math.random() * 10);
				
				if (conditionTerminal % 10 == 0) {
					PaymentTerminal terminal = place.getPaymentTerminal();
					terminal.setStatus(PaymentTerminalStatus.BROKEN);
					
					log.append(" Терминал №" + terminal.getId() + " сломался\n");
					log.setCaretPosition(log.getText().length());
					
					while(terminal.getStatus() == PaymentTerminalStatus.BROKEN) {
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.getMessage();
						}
					}
					terminal.setStatus(PaymentTerminalStatus.WORK);
					Budgeting.REPAIRS += 200;
					
					log.append(" Терминал №" + terminal.getId() + " починили!\n");
					log.setCaretPosition(log.getText().length());
				}
				Budgeting.INCOME += 500;
				
				int conditionBarrier = (int) (Math.random() * 10);
				
				if (conditionBarrier % 10 == 0) {
					Barrier barrier = place.getBarrier();
					barrier.setStatus(BarrierStatus.BROKEN);
					
					log.append(" Шлагбаум №" + barrier.getId() + " сломался\n");
					log.setCaretPosition(log.getText().length());
					
					while(barrier.getStatus() == BarrierStatus.BROKEN) {
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.getMessage();
						}
					}
					barrier.setStatus(BarrierStatus.WORK);
					Budgeting.REPAIRS += 100;
					
					log.append(" Шлагбаум №" + barrier.getId() + " починили!\n");
					log.setCaretPosition(log.getText().length());
				}
				
				int time = new Random().nextInt(60000 - 30000) + 30000;
				try {
					sleep(time);
				} catch (InterruptedException e) {
					e.getMessage();
				}
				place.addTime((int) (time / 1000));
				
				Car car = place.getQueue().get(0);
				log.append(" Машина №" + car.getId() + " выехала из " + place.getId());
				place.getQueue().remove(0);
				log.append(". Очередь составляет - " + place.getQueue().size() + "\n");
				
				if (place.getQueue().isEmpty()) {
					place.setStatus(PlaceStatus.DONTWORK);
				}	
			}
			else {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		}	
	}
}
