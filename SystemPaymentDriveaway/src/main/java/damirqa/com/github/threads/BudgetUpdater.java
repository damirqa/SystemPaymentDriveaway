package damirqa.com.github.threads;

import java.util.ArrayList;

import javax.swing.JTextField;

import damirqa.com.github.storage.Budgeting;
import damirqa.com.github.storage.Statistics;

import static java.lang.Thread.sleep;

public class BudgetUpdater implements Runnable{

	private JTextField income;
	private JTextField repairs;
	private JTextField profit;
	
	public BudgetUpdater(ArrayList<JTextField> fields) {
		this.income = fields.get(0);
		this.repairs = fields.get(1);
		this.profit = fields.get(2);
	}
	
	public void run() {
		while(true) {
			income.setText(Budgeting.INCOME + " руб.");
			repairs.setText(Budgeting.REPAIRS + " руб.");
			Budgeting.PROFIT = Budgeting.INCOME - Budgeting.REPAIRS - Statistics.SPENT * 3;
			profit.setText(Budgeting.PROFIT + " руб.");
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}

}
