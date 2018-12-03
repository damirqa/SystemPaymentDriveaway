package damirqa.com.github.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import static java.lang.Thread.sleep;

public class timeClockThread implements Runnable{

	private JLabel time;
	
	public timeClockThread(JLabel time) {
		this.time = time;
	}
	
	@Override
	public void run() {
		while(true) {
			Date date = new Date();
			SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM HH:mm:ss");
			time.setText(dt1.format(date));
			try {
				sleep(1000);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

}
