/*
 * Class Description: This class is the where the main program starts so the user will login and if successful, it will output the application
 */

package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.utils.HistogramViewer;
import cryptoTrader.utils.TableViewer;

public class MainUI extends JFrame implements ActionListener {
	/**
	 * change
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
//	private JTextArea tickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	private String inputtedUserName = "";
	private String inputtedPassword = "";
	private static userDataBase usersData = userDataBase.getInstance();
	private ArrayList<Object> tradersInfo = new ArrayList<Object>();
	private TradeBrokerDatabase tradersData = TradeBrokerDatabase.getInstance();
	private PerformTradeProxy performTrade = PerformTradeProxy.getInstance();
	
	// returns the instance of MainUI - implements a singleton design pattern
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();
		return instance;
	}
	
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar


		JPanel north = new JPanel();

//		north.add(strategyList);

		// Set bottom bar
//		JLabel from = new JLabel("From");
//		UtilDateModel dateModel = new UtilDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
//		@SuppressWarnings("serial")
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
//			private String datePatern = "dd/MM/yyyy";
//
//			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
//
//			@Override
//			public Object stringToValue(String text) throws ParseException {
//				return dateFormatter.parseObject(text);
//			}
//
//			@Override
//			public String valueToString(Object value) throws ParseException {
//				if (value != null) {
//					Calendar cal = (Calendar) value;
//					return dateFormatter.format(cal.getTime());
//				}
//
//				return "";
//			}
//		});

		final JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		
		trade.addActionListener(this);
		
		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}
	

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		
		// set up the login window
		final JFrame loginFrame = new JFrame("Login");
		loginFrame.setSize(400, 180);
		loginFrame.setLocation(400,250);
		
		// create labels for the frame
		JLabel label1 = new JLabel(" Username:");
		label1.setBounds(1,10,80,20);
		JLabel label2 = new JLabel(" Password:");
		label2.setBounds(1,30,80,20);
	
		// create the submit button for the frame
		JButton login = new JButton("Submit!");
		login.setBounds(150,100,100, 20);
		
		// create the text field boxes for the frame
		final JTextField user = new JTextField(20);
		user.setBounds(75,10,200, 20);
		final JTextField pass = new JTextField(20);
		pass.setBounds(75,60,200, 20);
		
		// add labels, button and text field boxes to the frame
		loginFrame.add(user);
		loginFrame.add(pass);
		loginFrame.add(login);
		loginFrame.add(label1);
		loginFrame.add(label2);
		
		loginFrame.setVisible(true);
		
		// if the login button is pressed, it will check credentials of user name and password
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String getUser = user.getText();
				String getPass = pass.getText();
				
				// checks credentials
				boolean correct = usersData.checkCredentials(getUser, getPass);
				
				// if correct credentials, the main application will appear
				if (correct == true) {
					loginFrame.setVisible(false);
					JFrame frame = MainUI.getInstance();
					frame.setSize(900, 600);
					frame.pack();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				// if incorrect, an error message will appear and will then close the program
				else {
					loginFrame.setVisible(false);
					JFrame errorFrame = new JFrame("Error");
					errorFrame.setSize(300,100);
					errorFrame.setLocation(400,250);
					JLabel label3 = new JLabel("You inputted the wrong credentials. Please exit.");
					label3.setBounds(1,10,80,20);
					errorFrame.add(label3);
					errorFrame.setVisible(true);
					errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					
					// create a trader object with their name, coin name list and strategy
					ArrayList<Object> trader = new ArrayList<Object>();
					trader.add(traderName);
					trader.add(Arrays.toString(coinNames));
					trader.add(strategyName);
					
					// add each trader to tradersInfo which is an array list that holds all the trader objects
					tradersInfo.add(trader);
					
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
					
					// if there are duplicates in trader names, it will show an error message
					if (this.verifyName(trader) == false) {
						tradersInfo.removeAll(tradersInfo);
						JOptionPane.showMessageDialog(this, "Name already exists. Please enter a valid name on line " + (count + 1) );
						return;
					}
					
	        }
			// sends tradersInfo to the trader broker database
			tradersData.setTradersData(tradersInfo);
			
			stats.removeAll();
			HistogramViewer bar = new HistogramViewer();
			TableViewer table  = new TableViewer(); 
			// performs the trade
			performTrade.execute();
			table.update1(); 
			bar.update();
			tradersInfo.removeAll(tradersInfo);
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}
	
	/*
	 * This method  checks if there are duplicates in the traders' names 
	 * @param it is the trader object which holds the trader's name, coin list and strategy type
	 * @return true if their are no duplicates and false if there is a duplicate in traders' names
	 */
	public boolean verifyName(ArrayList<Object> trader) {
		int index = tradersInfo.indexOf(trader);
		String traderName = (String) trader.get(0);
		for (int i = 0; i<tradersInfo.size();i++) {
			if (index != i) {
				ArrayList<Object> checkTrader = (ArrayList<Object>) tradersInfo.get(i);
				String checkName = (String) checkTrader.get(0);
				if (traderName.equals(checkName)) {
					return false;
				}
			}		
		}
		return true;
	}
	

}

