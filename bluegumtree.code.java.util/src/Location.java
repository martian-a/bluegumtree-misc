package uk.co.bluegumtree.code.java.util;

import java.io.File;
import java.io.IOException;

public class Location {

	/**
	 * @return a system-friendly path to the directory that will be/has been
	 * used for saving data associated with this app.
	 */
	public static String getSaveDir(String dirName) {

		String currPath = Location.getUserHome();

		if (currPath == null) {
			currPath = Location.getUserDir();
		}

		if (currPath == null) {
			currPath = "";
		}

		currPath = currPath + File.separator + dirName;

		return currPath;
	}

	/**
	 * @return a system-friendly path to the directory that this app is being
	 * run in.
	 */
	public static String getUserDir() {

		try {

			// Try to get the path to the current directory
			return new File(System.getProperty("user.dir")).getCanonicalPath();

		} catch (IOException eCurrentDir) {
			// TODO: Investigate additional fallback options
			return null;
		}
	}

	/**
	 * @return a system-friendly path to the user's home directory.
	 */
	public static String getUserHome() {

		try {

			// Try to get the path of the user's home directory
			return new File(System.getProperty("user.home")).getCanonicalPath();

		} catch (IOException eHomeDir) {

			return null;
		}
	}

}
