package com.mukhar.commons;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to read a data file.
 */
public class LineDataReader {
	private BufferedReader br;

	/**
	 * Read a data file and return the contents as a list of Strings. No error
	 * checking is performed.
	 * 
	 * @param f
	 *          The data file to read
	 * @return The data from the file as a list of strings.
	 */
	public ArrayList<String> readFile(int numLines) {
		ArrayList<String> result = new ArrayList<String>(numLines);
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (result.size() < numLines && s != null) {
			try {
				result.add(s);
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void closeFile() {
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openFile(File f) {
		try {
			FileReader fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
