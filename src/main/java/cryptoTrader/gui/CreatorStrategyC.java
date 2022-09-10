/*
 * This class is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyC object 
 */

package cryptoTrader.gui;

public class CreatorStrategyC extends CreatorStrategy {
	
	// returns ProductStrategyC object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyC();
	}	

}
