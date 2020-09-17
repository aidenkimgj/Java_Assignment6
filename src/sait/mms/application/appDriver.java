package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;
/**
 * This class have main method to generate this program.
 * 
 * @author Hong and Kim
 * @version 1.0, Aug 4, 2020
 *
 */
public class appDriver {
	/**
	 * This is main method
	 * 
	 * @param  the args
	 * @throws SQLException - if the code inside main method doesn't work and can not find SQL data, it throws SQLException.
	 */
	public static void main(String[] args) throws SQLException {
		MovieManagementSystem manager = new MovieManagementSystem();
		manager.displayMenu();

	}

}
