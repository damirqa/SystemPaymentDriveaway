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
import damirqa.com.github.storage.Statistics;
import damirqa.com.github.threads.CarCreationThread;
import damirqa.com.github.threads.CarTrackerThread;
import damirqa.com.github.threads.ConditionTrackerThread;
import damirqa.com.github.threads.PlaceThread;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame {

	private int width = 800;
	private int height = 600;
	
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
