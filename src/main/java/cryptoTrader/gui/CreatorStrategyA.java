/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyA object
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public class CreatorStrategyA extends CreatorStrategy {
	
	// returns ProductStrategyA object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyA();

	}

}
