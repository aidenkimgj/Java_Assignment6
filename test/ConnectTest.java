
import java.sql.ResultSet;
import java.sql.SQLException;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;

/**
 * Class description: Simply connects and disconnects from the database.
 *
 * @author Hong and Kim
 * @version 1.0, Aug 4, 2020
 */
public class ConnectTest {
	/**
	 * Tests connecting to the database.
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseDriver driver = new MariaDBDriver();
		
		try {
			driver.connect();
			
			System.out.println("Connected!");
			
			ResultSet rs = driver.get("SELECT 'Hello from the other side!'");
			
			boolean hasRow = rs.next();
			
			if (hasRow) {
				String column1 = rs.getString(1);
				System.out.println(column1);
			} else {
				System.out.println("Ooops! No rows were found.");
			}
			
			driver.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
