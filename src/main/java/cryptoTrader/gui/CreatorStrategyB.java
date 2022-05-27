/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyB object
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public class CreatorStrategyB extends CreatorStrategy {
	
	// returns ProductStrategyB object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyB();

	}

}
