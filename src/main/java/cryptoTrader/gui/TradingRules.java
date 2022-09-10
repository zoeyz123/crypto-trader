/*
 * This class creates the TradingRules object to use with the corresponding strategy.
 */

package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;

public abstract class TradingRules {
	
	TradeBrokerDatabase coinPrices = TradeBrokerDatabase.getInstance();
	
	public abstract String doAlgorithm(TradingRules tradingRules);
	

}
