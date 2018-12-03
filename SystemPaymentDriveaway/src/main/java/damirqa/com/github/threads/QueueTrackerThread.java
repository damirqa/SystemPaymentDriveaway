package damirqa.com.github.threads;

import java.util.ArrayList;

import javax.swing.JLabel;

import damirqa.com.github.storage.Statistics;

public class QueueTrackerThread implements Runnable{

	private ArrayList<JLabel> queue;
	
	public QueueTrackerThread(ArrayList<JLabel> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			for(int i = 0; i < 5; i++) {
				int k = Statistics.PLACES.get(i).getQueue().size();
				queue.get(i).setText("Очередь " + (i + 1) + " - " + k + " шт.");
			}
		}
	}

}
