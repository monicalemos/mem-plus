package baseDados;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LigacaoBD {
	private Connection connection;
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String username = "root", password = "";
	private static final String dbname = "jdbc:mysql://localhost:3306/memdb";

	public LigacaoBD(String userd, String passd) {
		this.password = passd;
		this.username = userd;
	}

	public LigacaoBD() {
	}

	public void connect() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = (Connection) DriverManager.getConnection(dbname, username, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.out.println("Database connection not established");
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}


}
