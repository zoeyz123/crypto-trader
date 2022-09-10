/*
 * This class is a subclass of TradingRules. It creates the TradingRulesD object to use with Strategy-D. 
 */

package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;

public class TradingRulesD extends TradingRules {
	
	private DataFetcher API = new DataFetcher(); //get data from the API to get the prices and do computations
	private CurrentDate date = new CurrentDate(); //get the current date
	
	
	/*
	 * This method trigger the computation for the trading rules. This utilizes the Strategy design pattern.
	 * @param tradingRules object to get all the data and do the computation
	 * @return "D" if the buying request is successful, "F" otherwise
	 */
	public String doAlgorithm(TradingRules tradingRules) {
		double lunaPrice = API.getPriceForCoin("harmony", date.returnNumberDate());
		double ftmPrice = API.getPriceForCoin("avalanche-2", date.returnNumberDate());
		if ((lunaPrice < 2300) && (ftmPrice > 35)){
			return "D";
		}
		else {
			return "F";
		}
	}

}
