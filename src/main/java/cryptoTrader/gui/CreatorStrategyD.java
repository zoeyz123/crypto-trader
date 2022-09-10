/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyD object
 */

package cryptoTrader.gui;

public class CreatorStrategyD extends CreatorStrategy {
	
	// returns ProductStrategyD object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyD();
	}

}
