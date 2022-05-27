/*
 * Class Description: This class is the proxy class of the performTrade class. It holds a reference to the real performTrade object and executes perform trade when called 
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public class PerformTradeProxy extends PerformTradeSubject {
	private PerformTrade realPerformTrade;
	private static PerformTradeProxy tradeInstance;
	
	// returns the instance of PerformTradeProxy object - uses Singleton design pattern
	public static PerformTradeProxy getInstance() {
		if (tradeInstance == null)
			tradeInstance = new PerformTradeProxy();

		return tradeInstance;
	}
	
	// when the MainUI calls the execute method, it will create the actual PerformTrade class then call the execute method of the class
	public void execute() {
		if (realPerformTrade == null) {
			realPerformTrade = PerformTrade.getInstance();
		}
		
		realPerformTrade.execute();
	}
	
	// when data visualization creator calls this, it will call getSuccessData from the actual perform trade class
	public Object[][] getSuccessData(){
		return realPerformTrade.getSuccessData();
	}

}
