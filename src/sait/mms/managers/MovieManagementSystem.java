package sait.mms.managers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * The program has five methods to connect to AppDriver class and Movie class.
 * 
 * @author Hong and Kim
 * @version 1.0, Aug 4, 2020
 */
public class MovieManagementSystem {
	/**
	 * Three private instance fields.
	 */
	private Scanner keyboard;
	private DatabaseDriver mariaDb;
	private Movie movie;

	/**
	 * This is MovieManagementSystem class constructor, creates 3 objects.
	 */
	public MovieManagementSystem() {
		keyboard = new Scanner(System.in);
		mariaDb = new MariaDBDriver();
		movie = new Movie();
	}

	/**
	 * The displayMenu method shows main program menu.
	 * 
	 * @throws SQLException - if the code inside main method doesn't work, it throws
	 *                     SQLException.
	 */
	public void displayMenu() throws SQLException {
		int option = -1;
		while (option != 5) {
			System.out.println("************* Movie Management system **************");
			System.out.println();
			System.out.println("1\tAdd New Movie");
			System.out.println("2\tGenerate List of Movies Released in a Year");
			System.out.println("3\tGenerate List of Random Movies");
			System.out.println("4\tDelete the Movie");
			System.out.println("5\tExit");
			System.out.println();

			System.out.print("Enter an option: ");
			option = keyboard.nextInt();
			keyboard.nextLine();// Flush out the scanner Obj

			switch (option) {
			case 1:
				addMovie();
				break;
			case 2:
				printYearsMovies();
				break;
			case 3:
				printRandomMovies();
				break;
			case 4:
				deleteMovie();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input!");
				System.out.println();
			}

		}
	}
	/**
	 * This method allows additional movies to be registered in the database.
	 * 
	 * @throws SQLException - Create an exception if the data entering the database does not fit the format.
	 */
	public void addMovie() throws SQLException {
		System.out.print("Enter the duration: ");
		// store duration in the movie class.
		movie.setDuration(keyboard.nextInt());
		keyboard.nextLine();

		System.out.print("Enter the title: ");
		// store title in the movie class. 
		movie.setTitle(keyboard.nextLine());

		System.out.print("Enter the year: ");
		// store year in the movie class.
		movie.setYear(keyboard.nextInt());
		keyboard.nextLine();

		System.out.println();
		// query statement in order to make new row in the database.
		String query = "INSERT INTO MOVIES (duration, title, year) VALUES (" + movie.getDuration() + ", '"
				+ movie.getTitle() + "'," + movie.getYear() + ")";

		mariaDb.connect(); // connect MariaDB
		mariaDb.update(query); // execute query statement
		mariaDb.disconnect(); // disconnect MariaDB

		System.out.println("The movie has been added successfully.");
		System.out.println();
	}
	/**
	 * This method finds movies in the database based on the year.
	 * 
	 * @throws SQLException - Create an exception if the data entering the database does not fit the format.
	 */
	public void printYearsMovies() throws SQLException {
		System.out.print("Enter the year: ");
		movie.setYear(keyboard.nextInt());
		keyboard.nextLine();

		System.out.println();

		System.out.println("***************************  Movie **************************");
		System.out.printf("%-10s%-45s%5s%n", "Duration", "Title", "Year");
		
		// query statement
		String query = "SELECT * FROM MOVIES WHERE YEAR = " + movie.getYear();
		mariaDb.connect();
		// gets the results of the query statement executed
		ResultSet result = mariaDb.get(query);
		// search all the results, put each data into the movie class, and print it.
		while (result.next()) {
			movie.setDuration(result.getInt(2)); // gets the second column from the row into the int type.
			movie.setTitle(result.getString(3)); // gets the third column from the row into the Sting type.
			movie.setYear(result.getInt(4));
			System.out.println(movie);
		}
		mariaDb.disconnect();
		System.out.println();
	}
/**
 * This method pick a data from DB randomly
 * 
 * @throws SQLException -  Create an exception if the data entering the database does not fit the format.
 */
	public void printRandomMovies() throws SQLException {

		System.out.println();
		System.out.println("***************************  Movie **************************");
		System.out.printf("%-10s%-45s%5s%n", "Duration", "Title", "Year");
		// query statement
		String query = "SELECT * FROM MOVIES ORDER BY RAND() LIMIT 1";

		mariaDb.connect();
		// gets the results of the query statement executed
		ResultSet result = mariaDb.get(query);
		// earch all the results, put each data into the movie class, and print it.
		while (result.next()) {
			movie.setDuration(result.getInt(2));
			movie.setTitle(result.getString(3));
			movie.setYear(result.getInt(4));
			System.out.println(movie);
		}
		mariaDb.disconnect();
		System.out.println();

	}
/**
 * This method delete a data in DB
 * 
 * @throws SQLException -  Create an exception if the data entering the database does not fit the format.
 */
	public void deleteMovie() throws SQLException {

		System.out.print("Enter the ID: ");
		int id = keyboard.nextInt();
		keyboard.nextLine();
		
		// query statement to select all information from movies table. 
		String query1 = "SELECT * FROM MOVIES";
		mariaDb.connect();
		ResultSet result = mariaDb.get(query1);

		// find matched data.
		while (result.next()) {
			int number = result.getInt(1);

			if (id == number) {

				// query statement to delete data which has a same id as input.
				String query = "DELETE FROM MOVIES WHERE id=" + id;

				mariaDb.update(query);
				mariaDb.disconnect();

				System.out.println();
				System.out.println("ID " + id + " movie has been deleted.");
				System.out.println();
				return; 
			}
		}
		System.out.println();
		System.out.println("ID " + id + " does not exist.");
		System.out.println();
		deleteMovie(); // recursive call, in case of id not found in the database.
	}

}
