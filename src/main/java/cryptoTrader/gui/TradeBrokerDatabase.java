/*
 * Class Description: This class holds all the trader's information
 */

package cryptoTrader.gui;
import java.util.ArrayList;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;

public class TradeBrokerDatabase {
	private ArrayList<Object> tradersData = new ArrayList<Object>();
	private ArrayList<String> coinList;
	private AvailableCryptoList APIcoinList = AvailableCryptoList.getInstance();
	private DataFetcher getCoinPrices = new DataFetcher();
	private ArrayList<Object> coinPrices;
	private static TradeBrokerDatabase databaseInstance;
	private CurrentDate date = new CurrentDate();
	
	// constructor
	private TradeBrokerDatabase() {
	}
	
	public static TradeBrokerDatabase getInstance() {
		if (databaseInstance == null)
			databaseInstance = new TradeBrokerDatabase();

		return databaseInstance;
	}

	// this is where we would filter down to all the coins needed by all trader brokers
	private void makeCoinList() {
		for (int i=0;i<tradersData.size();i++) {
			ArrayList<Object> trader = (ArrayList<Object>) tradersData.get(i);
			String[] coinNames = (String[]) trader.get(1);
			for (int j=0; j< coinNames.length;j++) {
				if (!coinList.contains(coinNames[j])) {
					coinList.add(coinNames[j]);
				}
			}
		}
		verifyCoinList();
	}
	
	// verifies that the coins provided by the user actually exists in API, removes the ones that don't exist
	private void verifyCoinList() {
		String coinName;
		
		String[] availableCoins = APIcoinList.getAvailableCryptos();
		for (int i=0;i<coinList.size();i++) {
			coinName = coinList.get(i);
			int j;
			for (j = 0; j < availableCoins.length; j++) {
				if (coinName.equals(availableCoins[j])) {
					break;
				}
			}
			if (j== availableCoins.length) {
				coinList.remove(coinName);
			}
		}
	}
	
	// creates and returns the dictionary which holds an arraylist of arraylists which each arraylist having the name of the coin and it's corresponding price
	public ArrayList<Object> createPriceList(){
		for (int i=0;i<coinList.size();i++) {
			ArrayList<Object> coinPair = new ArrayList<Object>();
			coinPair.add(coinList.get(i));
			coinPair.add(getCoinPrices.getPriceForCoin(coinList.get(i), date.returnCurrentDate()));
			System.out.println(getCoinPrices.getPriceForCoin(coinList.get(i), date.returnCurrentDate()));
			coinPrices.add(coinPair);
		}
		return coinPrices;
	}
	
	// returns the trader's info
	public ArrayList<Object> getTradersData(){
		return tradersData;
	}
	
	/*
	 * MainUI will use this to set the tradersData created there and send it to trader broker database
	 * @param: an array list of trader's data
	 */
	public void setTradersData(ArrayList<Object> tradersData) {
		this.tradersData = tradersData;
	}	

}
