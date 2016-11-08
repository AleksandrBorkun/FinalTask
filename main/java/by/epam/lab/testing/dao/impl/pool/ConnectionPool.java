package by.epam.lab.testing.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5);

	private ConnectionPool() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			for (int index = 0; index < pool.remainingCapacity(); index++) {
				pool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/TESTING_APP?useSSL=false", "root",
						"root"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws InterruptedException {
		return pool.take();
	}

	public void returnConnection(Connection connection) throws SQLException, InterruptedException {

		if (connection == null) {
			return;
		}

		connection.setAutoCommit(true);
		connection.setReadOnly(false);

		pool.put(connection);
		return;
	}

	public void closePool() throws SQLException {

		for (Connection con : pool) {
			con.close();
		}
	}

	public static ConnectionPool getInstance() {

		return instance;
	}

}
