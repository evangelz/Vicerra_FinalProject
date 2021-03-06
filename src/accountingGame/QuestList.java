package accountingGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;

import accountingGame.npc.NPC;
import accountingGame.npc.QuestItem;

public class QuestList {
	
	private ArrayList<QuestTemplate> availableQuestList = new ArrayList();
	

	public ArrayList<QuestTemplate> getAvailableQuestList() {
		return availableQuestList;
	}

	public void setAvailableQuestList(ArrayList<QuestTemplate> availableQuestList) {
		this.availableQuestList = availableQuestList;
	}
	

	private final String userName = "root";
	private final String password = "12261992";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "accg";
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
	
	public void run(int playerID) {
		PreparedStatement pickQuest = null;
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
			ResultSet resultSet=null;
			ResultSetMetaData resultSetMetaData;

			pickQuest = conn.prepareStatement("select * from player_account A inner join player_skill_level B on A.player_id = B.player_id inner join quest C on B.skill_id = C.skill_id where C.skill_level = B.skill_level and B.player_id = ?");


			pickQuest.setString(1,playerID+"");
			resultSet = pickQuest.executeQuery();
			while(resultSet.next())
			{
				QuestTemplate quest =new QuestTemplate();
				quest.setQuestID(resultSet.getInt("quest_id"));
				quest.setQuestTitle(resultSet.getString("quest_name"));
				quest.setQuestID(resultSet.getInt("quest_id"));
				quest.setRequirement(resultSet.getString("quest_requirement"));
				quest.setQuestStory(resultSet.getString("quest_story"));

				for (int i = 12;i<=26;i++)

				{
					if (!(resultSet.getString(i).equals("0")))
					{
						resultSetMetaData = resultSet.getMetaData();
						QuestItem questItem = new QuestItem();
						questItem.setValue(resultSet.getString(i));
						quest.getQuestInformation().put(resultSetMetaData.getColumnName(i), questItem);
					}
				}
				NPC npc = new NPC();
				npc.setNPCName(resultSet.getString("npc"));
				npc.setDialogue(resultSet.getString("dialogue"));
				quest.setAnswer(resultSet.getString("quest_answer"));
				quest.setSkillLevel(resultSet.getInt("skill_level"));
				quest.setSkillID(resultSet.getInt("skill_id"));
				quest.getNpc().add(npc);
				availableQuestList.add(quest);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
