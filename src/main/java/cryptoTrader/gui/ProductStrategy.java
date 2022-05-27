/*
 * Class Description: This class creates the ProductStrategy object for the traders data
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public abstract class ProductStrategy { 
	
	public ProductStrategy() {
		
	}
	
	public abstract boolean verifyTrade (String[] coinList);
	
	public abstract String getName();

}
