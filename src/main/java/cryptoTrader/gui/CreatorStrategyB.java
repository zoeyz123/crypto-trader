/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyB object
 */

package cryptoTrader.gui;

public class CreatorStrategyB extends CreatorStrategy {
	
	// returns ProductStrategyB object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyB();

	}

}
