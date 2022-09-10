/*
 * Class Description: This class performs the trade which happens when the mainUI calls the execute method  
 */ 

package cryptoTrader.gui;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class PerformTrade extends PerformTradeSubject {
	
	private TradingRules tradingrules;
	private Object[][] allData;
	private ArrayList<Object[]> all = new ArrayList<Object[]>();
	private Strategy doStrategy;
	private static PerformTrade tradeInstance;
	private ArrayList<Object> tradersData = new ArrayList<Object>();
	private CurrentDate date = new CurrentDate();
	
	//constructor
	private PerformTrade() {
		doStrategy = new Strategy();		
	}
	
	// gets an instance of performTrade object - implements Singleton design pattern
	public static PerformTrade getInstance() {
		if (tradeInstance == null)
			tradeInstance = new PerformTrade();
		return tradeInstance;
	}
	
	/*
	 * This method sets the type of trading rule based on the strategy the user chooses
	 * @param the trading rule object that corresponds to the strategy the trader chose
	 */
	// 
	public void setTradingRule(TradingRules tradingrules) {
		this.tradingrules = tradingrules;
	}
	
	
	// all the operations to perform the trade
	public void execute() {
		
		String result = "";
		tradersData = doStrategy.getInfo(); 
		Boolean checkFail = false; // checks if the trader's coin list includes the coin name needed for the trading rule to work

		for (int i = 0; i < tradersData.size(); i++) {
			checkFail = false;
			ArrayList<Object> trader = (ArrayList<Object>) tradersData.get(i);
			ProductStrategy strategy = (ProductStrategy) trader.get(2);
			String [] coinList = trader.get(1).toString().split(",");
			
			// depending on the strategy chosen, if will check then verify if trade can work before doing the trading rule
			if (strategy.getName().equals("Strategy-A")){
				if (strategy.verifyTrade(coinList) == false) {
					checkFail = true;
					Object[] eachData = {trader.get(0), "Strategy-A","Null", "Fail", "Null", "Null", date.returnCurrentDate()};
					all.add(eachData);
					
				}
				else {
					tradingrules = new TradingRulesA();
					result = tradingrules.doAlgorithm(new TradingRulesA()); 
				}
			}
			else if (strategy.getName().equals("Strategy-B")) {
				if (strategy.verifyTrade(coinList) == false) {
					checkFail = true;
					Object[] eachData = {trader.get(0), "Strategy-B","Null", "Fail", "Null", "Null", date.returnCurrentDate()};
					all.add(eachData);
					
				}
				else {
					tradingrules = new TradingRulesB();
					result = tradingrules.doAlgorithm(new TradingRulesB()); 
				}
				
			}
			else if (strategy.getName().equals("Strategy-C")) {
				if (strategy.verifyTrade(coinList) == false) {
					checkFail = true;
					Object[] eachData = {trader.get(0), "Strategy-C","Null", "Fail", "Null", "Null", date.returnCurrentDate()};
					all.add(eachData);
					
				}
				else {
					tradingrules = new TradingRulesC();
					result = tradingrules.doAlgorithm(new TradingRulesC()); 
				}
			}
			else if (strategy.getName().equals("Strategy-D")) {
				if (strategy.verifyTrade(coinList) == false) {
					checkFail = true;
					Object[] eachData = {trader.get(0), "Strategy-D","Null", "Fail", "Null", "Null", date.returnCurrentDate()};
					all.add(eachData);
					
				}
				else {
					tradingrules = new TradingRulesD();
					result = tradingrules.doAlgorithm(new TradingRulesD()); 
				}
			}
			// depending on the result from the performing the trading rules, it will add the successful trade to an arraylist
			if (result.equals("A") && checkFail == false) {
				Object[] eachData = {trader.get(0), "Strategy-A","ETH", "Buy", "500", "150.3", date.returnCurrentDate()};
				all.add(eachData);
			}
			else if (result.equals("B") && checkFail == false) {
				Object[] eachData = {trader.get(0), "Strategy-B","ADA", "Sell", "20", "670.23", date.returnCurrentDate()};
				all.add(eachData);
			}
			else if (result.equals("C") && checkFail == false) {
				Object[] eachData = {trader.get(0), "Strategy-C","MANA", "Buy", "140", "820.32", date.returnCurrentDate()};
				all.add(eachData);
			}
			else if (result.equals("D") && checkFail == false) {
				Object[] eachData = {trader.get(0), "Strategy-D","LUNA", "Sell", "12", "400.65", date.returnCurrentDate()};
				all.add(eachData);
			}
			if (checkFail == true) {
				this.failMessage(trader.get(0));
			}
		}
		
		allData = new Object[all.size()][7];
		
		for (int i = 0; i < all.size(); i++) {
			allData[i] = all.get(i);
		}
		
	}
	
	// gets the successful trades and is used by data visualization creator 
	public Object[][] getSuccessData() {
		return allData;
	}
	
	/*
	 * This method will show a fail message window for trades that did not pass the verify trade test
	 * @param the trader's name
	 */
	public void failMessage(Object tradeName) {
		JFrame failFrame = new JFrame("Trade Fail");
		failFrame.setSize(300,150);
		failFrame.setLocation(400,250);
		JLabel label = new JLabel("  Trading Strategy could not be applied for " + tradeName);
		label.setBounds(1,10,80,20);
		failFrame.add(label);
		failFrame.setVisible(true);
	}

}
