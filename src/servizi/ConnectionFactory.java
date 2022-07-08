package servizi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://localhost:5432/multedb?currentSchema=multe";
	private static final String USER = "postgres";
	private static final String PASS = "admin";
	private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASS);
			logger.info("Connessione effettuata correttamente");
		} catch (SQLException e) {
			logger.error("Connessione non effettuata");
			e.printStackTrace();
		}
		return conn;
	}
}
