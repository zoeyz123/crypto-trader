/*
 * Class Description: This class is the interface that connects the PerformTradeProxy class and the PerformTrade class
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.gui;

public abstract class PerformTradeSubject {
	public abstract void execute(); // abstract method for execute - which does perform trade
	public abstract Object[][] getSuccessData(); // abstract method for getting all the successful trades after perform trade is completed
}
