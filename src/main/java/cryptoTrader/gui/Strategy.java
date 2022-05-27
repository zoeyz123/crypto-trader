/*
 * Class Description: This class creates the creatorStrategy object and provides the productStrategy object for the traders data
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

import java.util.ArrayList;

public class Strategy {
	
	private CreatorStrategy[] creators;
	private TradeBrokerDatabase traders = TradeBrokerDatabase.getInstance();
	private ArrayList<Object> TradersInfo = new ArrayList<Object>();

	// constructor
	public Strategy() {
		creators = new CreatorStrategy[4];
		// Create an array of creators
		creators[0] = new CreatorStrategyA();
	    creators[1] = new CreatorStrategyB();
	    creators[2] = new CreatorStrategyC();
	    creators[3] = new CreatorStrategyD();
	}
	
	// returns the tradersInfo with replacement of the strategy string name with the object of it's corresponding productStrategy object
	public ArrayList<Object> getInfo() {
		TradersInfo = traders.getTradersData();
	    for(int i = 0;i < TradersInfo.size(); i++) {
	    	ArrayList<Object> trader = (ArrayList<Object>) TradersInfo.get(i);
	    	if (trader.get(2).equals("Strategy-A")) {
	    		ProductStrategy product = creators[0].factoryMethod();
	    		trader.remove(2);
	    		trader.add(product);
	  
	    	}else if (trader.get(2).equals("Strategy-B")) {
	    		ProductStrategy product = creators[1].factoryMethod();
	    		trader.remove(2);
	    		trader.add(product);
	    		
	    		
	    	}else if (trader.get(2).equals("Strategy-C")) {
	    		ProductStrategy product = creators[2].factoryMethod();
	    		trader.remove(2);
	    		trader.add(product);
	    	}
	    	else if (trader.get(2).equals("Strategy-D")) {
	    		ProductStrategy product = creators[3].factoryMethod();
	    		trader.remove(2);
	    		trader.add(product);
	    	}
	    }	    
		return TradersInfo;
		
	}
	
	
}
