package accountingGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AccountManager {
	
	private String playerUsername;
	private String playerPassword;
	
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
	
	public void checkLogIn() {
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
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createAccount() {
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
	
	public String getPlayerUsername() {
		return playerUsername;
	}

	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public boolean isUserPassMatched() {
		return userPassMatched;
	}

	public void setUserPassMatched(boolean userPassMatched) {
		this.userPassMatched = userPassMatched;
	}

}
