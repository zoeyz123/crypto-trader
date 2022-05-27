PROJECT DESCRIPTION: 
This a trading system that allows the user to add trade brokers (name, coin list, and strategy) and the system will determine if the trade was successful

HOW TO BUILD:
1. Extract all from zip file
2. Import project folder "CryptoTrader" to Eclipse. Click File -> Import. Then under Maven folder, click Existing Maven Projects
3. Navigate to: src/main/java -> cryptoTrader.gui -> MainUI.java
4. If there are errors in line 58,59, and 60 in MainUI.java do this: on line 58, it has a red line under HashMap, hover over it and click "Change project compliance and JRE to 1.7" - this should fix error
5. Run MainUI.java to start the program

HOW TO START:
1. In the login window, enter the following information to correctly login into the system
	Correct username: cs
	Correct password: 2212
2. Enter information for each trader, make sure that the traders all have unique names
3. KEEP IN MIND: after pressing the perform trade button, the action log and graph will show up. BUT if all or some of the bars on the graph don't show up, adjust the range on the side of the chart upwards 
   - This is because the range is sometimes too big and the bar will get hidden