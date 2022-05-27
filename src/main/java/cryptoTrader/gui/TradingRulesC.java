/*
 * Class Description: This class is a subclass of TradingRules. It creates the TradingRulesC object to use with Strategy-C.
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */
package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;

public class TradingRulesC extends TradingRules {
	
	private DataFetcher API = new DataFetcher(); //get data from the API to get the prices and do computations
	private CurrentDate date = new CurrentDate(); //get the current date
	
	/*
	 * This method trigger the computation for the trading rules. This utilizes the Strategy design pattern.
	 * @param tradingRules object to get all the data and do the computation
	 * @return "C" if the buying request is successful, "F" otherwise
	 */
	public String doAlgorithm(TradingRules tradingRules) {
		double onePrice = API.getPriceForCoin("harmony", date.returnNumberDate());
		double avaxPrice = API.getPriceForCoin("avalanche-2", date.returnNumberDate());
		if ((onePrice < 2300) && (avaxPrice > 35)){
			return "C";
		}
		else {
			return "F";
		}
	}

}
