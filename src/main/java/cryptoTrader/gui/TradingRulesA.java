/*
 * Class Description: This class is a subclass of TradingRules. It creates the TradingRulesA object to use with Strategy-A.
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */
package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;

public class TradingRulesA extends TradingRules{
	
	private DataFetcher API = new DataFetcher(); //get data from the API to get the prices and do computations
	private CurrentDate date = new CurrentDate(); //get the current date
	
	/*
	 * This method trigger the computation for the trading rules. This utilizes the Strategy design pattern.
	 * @param tradingRules object to get all the data and do the computation
	 * @return "A" if the buying request is successful, "F" otherwise
	 */
	public String doAlgorithm(TradingRules tradingRules) {
		double btcPrice = API.getPriceForCoin("bitcoin", date.returnNumberDate());
		double ethPrice = API.getPriceForCoin("ethereum", date.returnNumberDate());
		if ((btcPrice > 50000) && (ethPrice > 2)){			
		return "A";
		}
		else {
			return "F";
		}
		
	}


}
