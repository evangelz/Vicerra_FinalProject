package accountingGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import accountingGame.npc.NPC;
import accountingGame.npc.QuestItem;

public class AccountManager {

	private boolean userPassMatched;
	private int playerID;
	private String playerNotes;
	private int activeQuestID;
	
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
	
	public void checkLogIn(String playerUsername, String playerPassword) {
		PreparedStatement logIn = null;
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
			logIn = conn.prepareStatement("select * from player_account where username = ? and password =?");
			logIn.setString(1,playerUsername);
			logIn.setString(2,playerPassword);
			resultSet = logIn.executeQuery();
			if(resultSet.first())
			{
				userPassMatched = true;
				playerID = resultSet.getInt("player_id");
				playerNotes  = resultSet.getString("quest_notes");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createAccount(String playerUsername, String playerPassword) {
		PreparedStatement signUp = null;
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
			int create;
			signUp = conn.prepareStatement("insert player_account (username, password, quest_notes) values(?,?,null)");
			signUp.setString(1,playerUsername);
			signUp.setString(2,playerPassword);
			create = signUp.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(String playerNotes, int playerID) {
		PreparedStatement signUp = null;
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
			int update;
			signUp = conn.prepareStatement("update player_account set quest_notes = ? where player_id = ?");
			signUp.setString(1,playerNotes);
			signUp.setString(1,playerID+"");
			update = signUp.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuest(int activeQuestID, int playerID)
	{
		PreparedStatement updateQuest = null;
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
			int update;
<<<<<<< HEAD
			updateQuest = conn.prepareStatement("insert active_quest values(?,?)");
			updateQuest.setString(1, playerID+"" );
			updateQuest.setString(2,activeQuestID+"");
			
=======
			updateQuest = conn.prepareStatement("update active_quest set quest_id = ? where player_id = ?");
			updateQuest.setString(1,activeQuestID+"");
			updateQuest.setString(2, playerID+"" );
>>>>>>> origin/master
			update = updateQuest.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeQuest(int playerID)
	{
		PreparedStatement updateQuest = null;
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
			int update;
			updateQuest = conn.prepareStatement("delete from active_quest where player_id = ?");
			//updateQuest.setString(1,activeQuestID+"");
			updateQuest.setString(1, playerID+"" );
			update = updateQuest.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateLevel(int activeQuestSkillLevel, int playerID, int skillID)
	{
		PreparedStatement updateQuest = null;
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
			int update;
			updateQuest = conn.prepareStatement("update player_skill_level set skill_level = ? where player_id = ? and skill_id = ?");
			updateQuest.setString(1,(activeQuestSkillLevel+1)+"");
			updateQuest.setString(2, playerID+"");
			updateQuest.setString(3,skillID+"");
			update = updateQuest.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public QuestTemplate getActiveQuest(int playerID) {
		PreparedStatement pickQuest = null;
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		try {
			ResultSet resultSet=null;
			ResultSetMetaData resultSetMetaData;
<<<<<<< HEAD
			pickQuest = conn.prepareStatement("select * from player_account A inner join active_quest B on B.player_id = A.player_id inner join quest C where C.quest_id = B.quest_id and A.player_id = ?");
=======
			pickQuest = conn.prepareStatement("select * from active_quest A inner join quest B on A.quest_id = B.quest_id inner join player_account C on C.player_id = A.player_id where C.player_id = ?");
>>>>>>> origin/master
			pickQuest.setString(1,playerID+"");
			resultSet = pickQuest.executeQuery();
			if(resultSet.first())
			{
				QuestTemplate quest =new QuestTemplate();
				quest.setQuestID(resultSet.getInt("quest_id"));
				activeQuestID = quest.getQuestID();
				quest.setQuestTitle(resultSet.getString("quest_name"));
				quest.setRequirement(resultSet.getString("quest_requirement"));
				quest.setQuestStory(resultSet.getString("quest_story"));
<<<<<<< HEAD
				for (int i = 11;i<=24;i++)
				{
					System.out.println(resultSet.getInt(i));
=======
				for (int i = 7;i<=20;i++)
				{
					//System.out.println(resultSet.getInt(i));
>>>>>>> origin/master
					if (resultSet.getInt(i)!=0)
					{
						
						resultSetMetaData = resultSet.getMetaData();
						QuestItem questItem = new QuestItem();
						questItem.setValue(resultSet.getInt(i));
						quest.getQuestInformation().put(resultSetMetaData.getColumnName(i), questItem);
					}
				}
				if (resultSet.getString("records").equals("y"))
				{
					QuestItem records = new QuestItem();
					records.setRecords(resultSet.getString("records"));
				}
				NPC npc = new NPC();
				npc.setNPCName(resultSet.getString("npc"));
				
				npc.setDialogue(resultSet.getString("dialogue"));
				quest.setAnswer(resultSet.getString("quest_answer"));
				quest.setSkillLevel(resultSet.getInt("skill_level"));
				quest.setSkillLevel(resultSet.getInt("skill_id"));
				quest.getNpc().add(npc);
				return quest;
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public boolean isUserPassMatched() {
		return userPassMatched;
	}

	public void setUserPassMatched(boolean userPassMatched) {
		this.userPassMatched = userPassMatched;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getPlayerNotes() {
		return playerNotes;
	}

	public void setPlayerNotes(String playerNotes) {
		this.playerNotes = playerNotes;
	}

	public int getActiveQuestID() {
		return activeQuestID;
	}

	public void setActiveQuestID(int activeQuestID) {
		this.activeQuestID = activeQuestID;
	}

}
