/*
 * This class is a subclass of class ProductStrategy. It creates the ProductStrategyB object for the traders data
 */

package cryptoTrader.gui;

import java.util.ArrayList;

public class ProductStrategyB extends ProductStrategy {
	private String strategyName;
	private ArrayList<String> strategyCoinList = new ArrayList<String>();
	private String coinName;
	
	public ProductStrategyB() {
		strategyName = "Strategy-B";
		coinName = "ADA";
	}
	/*
	 * This method verifies whether the user-inputted coinlist is valid for the strategy.
	 * @param user-inputted coinlist
	 * @return boolean true if the coinlist is valid, false otherwise
	 */
	public boolean verifyTrade(String[] coinList) {
		coinList[0] = coinList[0].toString().substring(1);
		coinList[coinList.length-1] = coinList[coinList.length-1].toString().substring(0, coinList[coinList.length-1].length()-1);
		for (int i = 0; i < coinList.length; i++) {
			if (coinName.equals(coinList[i].strip())) {
				return true;
			}
		}
		return false;
	}
	/*
	 * This get method return the name of the strategy
	 */
	public String getName() {
		return strategyName;
	}
	

}
