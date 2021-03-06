package damirqa.com.github.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import damirqa.com.github.enums.BarrierStatus;
import damirqa.com.github.enums.PaymentTerminalStatus;
import damirqa.com.github.models.Place;
import damirqa.com.github.storage.Budgeting;
import damirqa.com.github.storage.Statistics;
import damirqa.com.github.threads.BudgetUpdater;
import damirqa.com.github.threads.CarCreationThread;
import damirqa.com.github.threads.CarTrackerThread;
import damirqa.com.github.threads.ConditionTrackerThread;
import damirqa.com.github.threads.PlaceThread;
import damirqa.com.github.threads.QueueTrackerThread;
import damirqa.com.github.threads.timeClockThread;
import damirqa.com.github.threads.trackJob;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame {

	private int width = 800;
	private int height = 580;
	
	private JPanel contentPanel;
	
	private JLabel logLabel;
	private JScrollPane logScroll;
	private static JTextArea logArea;
	
	private JLabel fixBarrierLabel;
	private JTextField fixBarrierField;
	private JButton fixBarrierButton;

	private JLabel fixTerminalLabel;
	private JTextField fixTerinalField;
	private JButton fixTerminalButton;
	
	private JLabel incomeLabel;
	private JTextField incomeField;
	
	private JLabel repairsLabel;
	private JTextField repairsField;
	
	private JLabel profitLabel;
	private JTextField profitField;
	
	private static ArrayList<JTextField> budgetField = new ArrayList<JTextField>();
	
	private JLabel spentLabel;
	private static JTextField spentField;
	
	private JLabel priceLabel;
	private JTextField priceField;
	
	private JLabel terminal1;
	private JLabel terminal2;
	private JLabel terminal3;
	private JLabel terminal4;
	private JLabel terminal5;
	
	private static JLabel conditionTerminal1;
	private static JLabel conditionTerminal2;
	private static JLabel conditionTerminal3;
	private static JLabel conditionTerminal4;
	private static JLabel conditionTerminal5;
	
	private static ArrayList<JLabel> conditionTerminal = new ArrayList<JLabel>();

	private JLabel barrier1;
	private JLabel barrier2;
	private JLabel barrier3;
	private JLabel barrier4;
	private JLabel barrier5;
	
	private static JLabel conditionBarrier1;
	private static JLabel conditionBarrier2;
	private static JLabel conditionBarrier3;
	private static JLabel conditionBarrier4;
	private static JLabel conditionBarrier5;
	
	private static ArrayList<JLabel> conditionBarrier = new ArrayList<JLabel>();
	
	private JLabel queue1;
	private JLabel queue2;
	private JLabel queue3;
	private JLabel queue4;
	private JLabel queue5;

	private static ArrayList<JLabel> queue = new ArrayList<>();
	
	private static JLabel timeClock;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					
					for (int i = 0; i < 5; i++) {
						Place place = new Place();
						Statistics.PLACES.add(place);
						new Thread(new PlaceThread(place, logArea)).start();
						new Thread(new ConditionTrackerThread(place, conditionBarrier, conditionTerminal)).start();
					}
					
					Statistics.PLACES.get(0).setStatus();
					
					Thread carCreation = new Thread(new CarCreationThread());
					carCreation.start();
					
					Thread carTracker = new Thread(new CarTrackerThread(logArea));
					carTracker.start();
					
					Thread budgetUpdater = new Thread(new BudgetUpdater(budgetField));
					budgetUpdater.start();
					
					Thread jobTracker = new Thread(new trackJob(spentField));
					jobTracker.start();
					
					Thread timeThread = new Thread(new timeClockThread(timeClock));
					timeThread.start();
					
					Thread queueTracker = new Thread(new QueueTrackerThread(queue));
					queueTracker.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Система оплаты проезда по автостраде");
		setWindowToCenter();
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		logLabel = new JLabel("Информация");
		logLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		logLabel.setBounds(10, 11, 169, 38);
		contentPanel.add(logLabel);
		
		logArea = new JTextArea();
		logArea.setLineWrap(true);
		logArea.setEditable(false);
		
		logScroll = new JScrollPane(logArea);
		logScroll.setBounds(10, 50, 764, 173);
		contentPanel.add(logScroll);
		
		terminal1 = new JLabel("Терминал 1 -");
		terminal1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		terminal1.setBounds(13, 223, 95, 25);
		contentPanel.add(terminal1);
		
		conditionTerminal1 = new JLabel("Не работает");
		conditionTerminal1.setHorizontalAlignment(SwingConstants.CENTER);
		conditionTerminal1.setOpaque(true);
		conditionTerminal1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionTerminal1.setBackground(Color.YELLOW);
		conditionTerminal1.setBounds(110, 225, 90, 20);
		contentPanel.add(conditionTerminal1);
		conditionTerminal.add(conditionTerminal1);
		
		terminal2 = new JLabel("Терминал 2 -");
		terminal2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		terminal2.setBounds(13, 248, 95, 25);
		contentPanel.add(terminal2);
		
		conditionTerminal2 = new JLabel("Не работает");
		conditionTerminal2.setHorizontalAlignment(SwingConstants.CENTER);
		conditionTerminal2.setOpaque(true);
		conditionTerminal2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionTerminal2.setBackground(Color.YELLOW);
		conditionTerminal2.setBounds(110, 250, 90, 20);
		contentPanel.add(conditionTerminal2);
		conditionTerminal.add(conditionTerminal2);
		
		terminal3 = new JLabel("Терминал 3 -");
		terminal3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		terminal3.setBounds(13, 273, 95, 25);
		contentPanel.add(terminal3);
		
		conditionTerminal3 = new JLabel("Не работает");
		conditionTerminal3.setHorizontalAlignment(SwingConstants.CENTER);
		conditionTerminal3.setOpaque(true);
		conditionTerminal3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionTerminal3.setBackground(Color.YELLOW);
		conditionTerminal3.setBounds(110, 275, 90, 20);
		contentPanel.add(conditionTerminal3);
		conditionTerminal.add(conditionTerminal3);
		
		terminal4 = new JLabel("Терминал 4 -");
		terminal4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		terminal4.setBounds(13, 298, 95, 25);
		contentPanel.add(terminal4);
		
		conditionTerminal4 = new JLabel("Не работает");
		conditionTerminal4.setHorizontalAlignment(SwingConstants.CENTER);
		conditionTerminal4.setOpaque(true);
		conditionTerminal4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionTerminal4.setBackground(Color.YELLOW);
		conditionTerminal4.setBounds(110, 300, 90, 20);
		contentPanel.add(conditionTerminal4);
		conditionTerminal.add(conditionTerminal4);
		
		terminal5 = new JLabel("Терминал 5 -");
		terminal5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		terminal5.setBounds(13, 323, 95, 25);
		contentPanel.add(terminal5);
		
		conditionTerminal5 = new JLabel("Не работает");
		conditionTerminal5.setHorizontalAlignment(SwingConstants.CENTER);
		conditionTerminal5.setOpaque(true);
		conditionTerminal5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionTerminal5.setBackground(Color.YELLOW);
		conditionTerminal5.setBounds(110, 325, 90, 20);
		contentPanel.add(conditionTerminal5);
		conditionTerminal.add(conditionTerminal5);
		
		fixTerminalLabel = new JLabel("Починить термнал №");
		fixTerminalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixTerminalLabel.setBounds(13, 348, 160, 25);
		contentPanel.add(fixTerminalLabel);
		
		fixTerinalField = new JTextField();
		fixTerinalField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixTerinalField.setBackground(Color.WHITE);
		fixTerinalField.setBounds(173, 349, 27, 25);
		contentPanel.add(fixTerinalField);
		
		fixTerminalButton = new JButton("Починить терминал");
		fixTerminalButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixTerminalButton.setBounds(10, 375, 189, 25);
		contentPanel.add(fixTerminalButton);
		
		barrier1 = new JLabel("Шлагбаум 1 -");
		barrier1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barrier1.setBounds(230, 223, 100, 25);
		contentPanel.add(barrier1);
		
		conditionBarrier1 = new JLabel("Не работает");
		conditionBarrier1.setHorizontalAlignment(SwingConstants.CENTER);
		conditionBarrier1.setOpaque(true);
		conditionBarrier1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionBarrier1.setBackground(Color.YELLOW);
		conditionBarrier1.setBounds(330, 225, 90, 20);
		contentPanel.add(conditionBarrier1);
		conditionBarrier.add(conditionBarrier1);
				
		barrier2 = new JLabel("Шлагбаум 2 -");
		barrier2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barrier2.setBounds(230, 248, 100, 25);
		contentPanel.add(barrier2);
		
		conditionBarrier2 = new JLabel("Не работает");
		conditionBarrier2.setHorizontalAlignment(SwingConstants.CENTER);
		conditionBarrier2.setOpaque(true);
		conditionBarrier2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionBarrier2.setBackground(Color.YELLOW);
		conditionBarrier2.setBounds(330, 250, 90, 20);
		contentPanel.add(conditionBarrier2);
		conditionBarrier.add(conditionBarrier2);
		
		barrier3 = new JLabel("Шлагбаум 3 -");
		barrier3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barrier3.setBounds(230, 273, 100, 25);
		contentPanel.add(barrier3);
		
		conditionBarrier3 = new JLabel("Не работает");
		conditionBarrier3.setHorizontalAlignment(SwingConstants.CENTER);
		conditionBarrier3.setOpaque(true);
		conditionBarrier3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionBarrier3.setBackground(Color.YELLOW);
		conditionBarrier3.setBounds(330, 275, 90, 20);
		contentPanel.add(conditionBarrier3);
		conditionBarrier.add(conditionBarrier3);
		
		barrier4 = new JLabel("Шлагбаум 4 -");
		barrier4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barrier4.setBounds(230, 298, 100, 25);
		contentPanel.add(barrier4);
		
		conditionBarrier4 = new JLabel("Не работает");
		conditionBarrier4.setHorizontalAlignment(SwingConstants.CENTER);
		conditionBarrier4.setOpaque(true);
		conditionBarrier4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionBarrier4.setBackground(Color.YELLOW);
		conditionBarrier4.setBounds(330, 300, 90, 20);
		contentPanel.add(conditionBarrier4);
		conditionBarrier.add(conditionBarrier4);
		
		barrier5 = new JLabel("Шлагбаум 5 -");
		barrier5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barrier5.setBounds(230, 323, 100, 25);
		contentPanel.add(barrier5);
		
		conditionBarrier5 = new JLabel("Не работает");
		conditionBarrier5.setHorizontalAlignment(SwingConstants.CENTER);
		conditionBarrier5.setOpaque(true);
		conditionBarrier5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		conditionBarrier5.setBackground(Color.YELLOW);
		conditionBarrier5.setBounds(330, 325, 90, 20);
		contentPanel.add(conditionBarrier5);
		conditionBarrier.add(conditionBarrier5);
		
		fixBarrierLabel = new JLabel("Починить шлагбаум №");
		fixBarrierLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixBarrierLabel.setBounds(230, 348, 168, 25);
		contentPanel.add(fixBarrierLabel);
		
		fixBarrierField = new JTextField();
		fixBarrierField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixBarrierField.setBackground(Color.WHITE);
		fixBarrierField.setBounds(400, 349, 22, 25);
		contentPanel.add(fixBarrierField);
		
		fixBarrierButton = new JButton("Починить шлагбаум");
		fixBarrierButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		fixBarrierButton.setBounds(230, 375, 190, 25);
		contentPanel.add(fixBarrierButton);
		
		incomeLabel = new JLabel("Доходы:");
		incomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		incomeLabel.setBounds(13, 405, 60, 20);
		contentPanel.add(incomeLabel);
		
		incomeField = new JTextField();
		incomeField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		incomeField.setBackground(Color.WHITE);
		incomeField.setEditable(false);
		incomeField.setBounds(75, 405, 115, 20);
		contentPanel.add(incomeField);
		budgetField.add(incomeField);
		
		repairsLabel = new JLabel("Ремонт:");
		repairsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		repairsLabel.setBounds(13, 430, 60, 20);
		contentPanel.add(repairsLabel);
		
		repairsField = new JTextField();
		repairsField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		repairsField.setBackground(Color.WHITE);
		repairsField.setEditable(false);
		repairsField.setBounds(75, 430, 115, 20);
		contentPanel.add(repairsField);
		budgetField.add(repairsField);
		
		spentLabel = new JLabel("Потрачено кВт/ч:");
		spentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spentLabel.setBounds(13, 455, 125, 20);
		contentPanel.add(spentLabel);
		
		spentField = new JTextField();
		spentField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spentField.setBackground(Color.WHITE);
		spentField.setEditable(false);
		spentField.setBounds(140, 455, 50, 20);
		contentPanel.add(spentField);
		
		profitLabel = new JLabel("Прибыль:");
		profitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		profitLabel.setBounds(13, 480, 70, 20);
		contentPanel.add(profitLabel);
		
		profitField = new JTextField();
		profitField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		profitField.setBackground(Color.WHITE);
		profitField.setEditable(false);
		profitField.setBounds(90, 480, 100, 20);
		contentPanel.add(profitField);
		budgetField.add(profitField);
		
		priceLabel = new JLabel("Цена 1 кВ/Ч = 3 руб.");
		priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		priceLabel.setBounds(13, 505, 150, 20);
		contentPanel.add(priceLabel);
		
		timeClock = new JLabel();
		timeClock.setFont(new Font("Segoe UI", Font.PLAIN, 80));
		timeClock.setBounds(230, 405, 550, 100);
		contentPanel.add(timeClock);
		
		queue1 = new JLabel("Очередь 1 - ");
		queue1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		queue1.setBounds(450, 223, 130, 25);
		contentPanel.add(queue1);
		queue.add(queue1);

		queue2 = new JLabel("Очередь 2 - ");
		queue2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		queue2.setBounds(450, 248, 130, 25);
		contentPanel.add(queue2);
		queue.add(queue2);
		
		queue3 = new JLabel("Очередь 3 - ");
		queue3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		queue3.setBounds(450, 273, 130, 25);
		contentPanel.add(queue3);
		queue.add(queue3);
		
		queue4 = new JLabel("Очередь 4 - ");
		queue4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		queue4.setBounds(450, 298, 130, 25);
		contentPanel.add(queue4);
		queue.add(queue4);
		
		queue5 = new JLabel("Очередь 5 - ");
		queue5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		queue5.setBounds(450, 323, 130, 25);
		contentPanel.add(queue5);
		queue.add(queue5);
		
		
		fixTerminalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(fixTerinalField.getText()) - 1;
				Statistics.PLACES.get(id).getPaymentTerminal().setStatus(PaymentTerminalStatus.WORK);
				fixTerinalField.setText("");
			}
		});
		
		fixBarrierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(fixBarrierField.getText()) - 1;
				Statistics.PLACES.get(id).getBarrier().setStatus(BarrierStatus.WORK);
				fixBarrierField.setText("");
			}
		});
	}
	
	private void setWindowToCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.width) / 2;
		int y = (screenSize.height - this.height) / 2;
		setBounds(x, y, width, height);
		
	}
}
