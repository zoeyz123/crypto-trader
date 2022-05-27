/**
 * Class Description: This class fetches the coinGecko API information for given coin on given date stored in a Json file
 * @param id of the coin to get info for
 * @param date to get info from
 * @return Json file with data from coingecko API
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataFetcher {

	private JsonObject getDataForCrypto(String id, String date) {

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	/**
	 * This class gets the price of a given coin based on a given date
	 * @param id of the cryptocoin to retrieve data for 
	 * @param date to get data from 
	 * @return price of cryptocoin as a double 
	 */
	public double getPriceForCoin(String id, String date) {
		double price = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}
		
		return price;
	}
	
	/**
	 * This class gets the market cap of a given cryptocoin based on a specific date 
	 * @param id of the cryptocoin to retrieve data for 
	 * @param date to get data from 
	 * @return market cap of cryptocoin as a double 
	 */
	public double getMarketCapForCoin(String id, String date) {
		double marketCap = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCap = currentPrice.get("cad").getAsDouble();
		}
		
		return marketCap;
	}
	
	/**
	 * This class gets the volume for coin of a given cryptocoin based on a specific date 
	 * @param id of the cryptocoin to retrieve data for 
	 * @param date to get data from 
	 * @return volume for coin of cryptocoin as a double 
	 */
	public double getVolumeForCoin(String id, String date) {
		double volume = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volume = currentPrice.get("cad").getAsDouble();
		}
		
		return volume;
	}
	
	public static void main(String[] args) {
//		DataFetcher fetcher = new DataFetcher();
//		double price = fetcher.getPriceForCoin("harmony", "08-10-2021");
//		double marketCap = fetcher.getMarketCapForCoin("bitcoin", "08-09-2021");
//		double volume = fetcher.getVolumeForCoin("bitcoin", "08-09-2021");
//		
//		System.out.println("Bitcoin=>\tPrice: " + price + 
//								"\n\t\tMarket Cap: " + marketCap + 
//								"\n\t\tVolume: "+volume);
//		
	}
}
