package damirqa.com.github.threads;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;

import damirqa.com.github.enums.BarrierStatus;
import damirqa.com.github.enums.PaymentTerminalStatus;
import damirqa.com.github.models.Barrier;
import damirqa.com.github.models.PaymentTerminal;
import damirqa.com.github.models.Place;

import static java.lang.Thread.sleep;

public class ConditionTrackerThread implements Runnable{

	private Barrier barrier;
	private PaymentTerminal terminal;
	private int id;
	private JLabel conditionBarrier;
	private JLabel conditionTermianl;
	
	public ConditionTrackerThread(Place place, List<JLabel> conditionBarrier, List<JLabel> conditionTerminal) {
		this.barrier = place.getBarrier();
		this.terminal = place.getPaymentTerminal();
		this.id = place.getId() - 1;
		this.conditionBarrier = conditionBarrier.get(id);
		this.conditionTermianl = conditionTerminal.get(id);
	}
	
	public void run() {
		while(true) {
			
			BarrierStatus barrierStatus = barrier.getStatus();
			switch(barrierStatus) {
				case WORK:
					conditionBarrier.setText("Работает");
					conditionBarrier.setForeground(Color.BLACK);
					conditionBarrier.setBackground(Color.GREEN);
					break;
				case DONTWORK:
					conditionBarrier.setText("Не работает");
					conditionBarrier.setForeground(Color.BLACK);
					conditionBarrier.setBackground(Color.YELLOW);
					break;
				case BROKEN:
					conditionBarrier.setText("Сломан");
					conditionBarrier.setForeground(Color.WHITE);
					conditionBarrier.setBackground(Color.RED);
					break;
			}
			
			PaymentTerminalStatus terminalStatus = terminal.getStatus();
			switch(terminalStatus) {
				case WORK:
					conditionTermianl.setText("Работает");
					conditionTermianl.setForeground(Color.BLACK);
					conditionTermianl.setBackground(Color.GREEN);
				break;
				case DONTWORK:
					conditionTermianl.setText("Не работает");
					conditionTermianl.setForeground(Color.BLACK);
					conditionTermianl.setBackground(Color.YELLOW);
					break;
				case BROKEN:
					conditionTermianl.setText("Сломан");
					conditionTermianl.setForeground(Color.WHITE);
					conditionTermianl.setBackground(Color.RED);
					break;
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			
		}
	}

}
