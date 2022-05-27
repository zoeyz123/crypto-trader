/*
 * Class Description: This is a subclass of CreatorStrategy. It overrides factoryMethod and returns a ProductStrategyC object 
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public class CreatorStrategyC extends CreatorStrategy {
	
	// returns ProductStrategyC object
	public ProductStrategy factoryMethod() {
		return new ProductStrategyC();
	}	

}
