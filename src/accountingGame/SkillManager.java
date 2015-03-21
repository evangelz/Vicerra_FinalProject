package accountingGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SkillManager {
	
	private String playerID;
	
	private boolean userPassMatched;
	
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
	
	
	public void createAccount() {
		PreparedStatement insertSkill = null;
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
			insertSkill = conn.prepareStatement("insert skill_level (skill_level, skill_id, player_id) values(1,?,?)");
			insertSkill.setString(1,"1");
			insertSkill.setString(2,playerID);
			create = insertSkill.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerPassword) {
		this.playerID = playerPassword;
	}

	public boolean isUserPassMatched() {
		return userPassMatched;
	}

	public void setUserPassMatched(boolean userPassMatched) {
		this.userPassMatched = userPassMatched;
	}

}

