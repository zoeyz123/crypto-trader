/*
 * Class Description: This class is a subclass of TradingRules. It creates the TradingRulesB object to use with Strategy-B.
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */
package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;

public class TradingRulesB extends TradingRules {
	
	private CurrentDate date = new CurrentDate(); //get data from the API to get the prices and do computations
	private DataFetcher API = new DataFetcher(); //get the current date
	
	
	/*
	 * This method trigger the computation for the trading rules. This utilizes the Strategy design pattern.
	 * @param tradingRules object to get all the data and do the computation
	 * @return "B" if the buying request is successful, "F" otherwise
	 */
	public String doAlgorithm(TradingRules tradingRules) {
		double usdPrice = API.getPriceForCoin("usd-coin", date.returnNumberDate());
		double solPrice = API.getPriceForCoin("solana", date.returnNumberDate());
		if ((usdPrice > 100) && (solPrice > 20)){
			return "B";
		}
		else {
			return "F";
		}
	}

}
