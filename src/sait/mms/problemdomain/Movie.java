package sait.mms.problemdomain;

/**
 * This class is a Movie class that can store and give data.
 * 
 * @author Hong and Kim
 * @version 1.0, Aug 4, 2020
 *
 */
public class Movie {

	private int duration;
	private String title;
	private int year;

	/**
	 * The getDuration method returns a Movie object's duration.
	 * 
	 * @return year - Returns movie's duration.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * The setDuration method get duration from input function and then store it.
	 * @param duration - get this parameter from the user's input
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * The getTitle method returns a Movie object's title.
	 * @return title - Returns movie's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * The getTitle method get title from input function and then store it.
	 * @param title - get this parameter from the user's input
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * The getYear method returns a Movie object's year.
	 * @return Returns movie's year. 
	 */
	public int getYear() {
		return year;
	}

	/**
	 * The setYear method get year from input function and then store it.
	 * @param year - get this parameter from the user's input
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * This method overrides from the Object class to print information for users.
	 * @return str - returns String value.
	 */
	@Override
	public String toString() {
		String str = "";
		str += String.format("%-10d%-45s%5d", duration, title, year);
		return str;
	}

}
