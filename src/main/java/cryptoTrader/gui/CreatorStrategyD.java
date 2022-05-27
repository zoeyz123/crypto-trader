/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyD object
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public class CreatorStrategyD extends CreatorStrategy {
	
	// returns ProductStrategyD object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyD();
	}

}
