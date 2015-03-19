package accountingGame;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;*/

import java.util.ArrayList;

import accountingGame.npc.NPC;
import accountingGame.npc.QuestItem;

public class QuestList {
	private ArrayList<QuestTemplate> completeQuestList = new ArrayList();
	private ArrayList<QuestTemplate> availableQuestList = new ArrayList();
	
	QuestTemplate classifyALEBaker1;
	
	public QuestList()
	{
		completeQuestList.add(classifyALEBakerRequest1());
	}
	
	public ArrayList<QuestTemplate> getCompleteQuestList() {
		return completeQuestList;
	}

	public void setCompleteQuestList(ArrayList<QuestTemplate> completeQuestList) {
		this.completeQuestList = completeQuestList;
	}

	public ArrayList<QuestTemplate> getAvailableQuestList() {
		return availableQuestList;
	}

	public void setAvailableQuestList(ArrayList<QuestTemplate> availableQuestList) {
		this.availableQuestList = availableQuestList;
	}
	
	public QuestTemplate classifyALEBakerRequest1()
	{
		QuestItem cash = new QuestItem();
		QuestItem inventory = new QuestItem();
		QuestItem equipment = new QuestItem();
		QuestItem accountsPayable = new QuestItem();
		QuestItem equity = new QuestItem();
		NPC baker = new NPC();
		cash.setValue(10000);
		inventory.setValue(30000);
		equipment.setValue(40000);
		accountsPayable.setValue(5000);
		equity.setValue(75000);
		QuestItem storeRecord = new QuestItem();
		storeRecord.getRecords().add(cash);
		storeRecord.getRecords().add(inventory);
		storeRecord.getRecords().add(equipment);
		storeRecord.getRecords().add(accountsPayable);
		storeRecord.getRecords().add(equity);
		storeRecord.setInfo("The list contains the following accounts: cash, inventory, equipment, accounts payable, equity");
		
		baker.setDialogue("I 'm having trouble in telling which of my accounts belong to liabilities. You can find the list of accounts at the back of the counter");
		classifyALEBaker1.getQuestInformation().add(storeRecord);
		classifyALEBaker1.setType("Assets/Liabilites/Equity Classification");
		classifyALEBaker1.setRequirement("Mr. Baker is having trouble sorting his accounts in his bakery business");
		classifyALEBaker1.setQuestTitle("Mr. Baker's First Request");
		classifyALEBaker1.getNpc().add(baker);
		classifyALEBaker1.setAnswer("Accounts Payable");
		
		return classifyALEBaker1;
	}


	public class DB_Exer1 {

		/** The name of the MySQL account to use (or empty for anonymous) */
		private final String userName = "root";

		/** The password for the MySQL account (or empty for anonymous) */
		private final String password = "";

		/** The name of the computer running MySQL */
		private final String serverName = "localhost";

		/** The port of the MySQL server (default is 3306) */
		private final int portNumber = 3306;

		/** The name of the database we are testing with (this default is installed with MySQL) */
		private final String dbName = "accg";
		
		/** The name of the table we are testing with */
		private final String tableName = "JDBC_TEST";
		
		public Connection getConnection() throws SQLException {
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", this.userName);
			connectionProps.put("password", this.password);

			conn = DriverManager.getConnection("jdbc:mysql://"
					+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
					connectionProps);

			return conn;
		}
		
		public boolean executeUpdate(Connection conn, String command) throws SQLException {
		    Statement stmt = null;
		    try {
		        stmt = conn.createStatement();
		        stmt.executeUpdate(command); // This will throw a SQLException if it fails
		        return true;
		    } finally {

		    	// This will run whether we throw an exception or not
		        if (stmt != null) { stmt.close(); }
		    }
		}
	}
	
	public void run() {
		PreparedStatement showQuest = null;
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			ResultSet resultSet = null;
			String getTable = "quest";
			showQuest = conn.prepareStatement("select * from quest");
			//showQuest.setString(1);
			resultSet = showQuest.executeQuery();
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("quest_name"));
				System.out.println(resultSet.getString("quest_requirement"));
				System.out.println(resultSet.getString("quest_information"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
