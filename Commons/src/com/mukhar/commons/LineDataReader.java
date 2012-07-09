package com.mukhar.commons;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to read a data file.
 */
public class LineDataReader {
  /**
   * Read a data file and return the contents as a list of Strings. No error
   * checking is performed.
   * 
   * @param f
   *          The data file to read
   * @return The data from the file as a list of strings.
   */
  public ArrayList<String> readFile(File f)
  {
    ArrayList<String> result = new ArrayList<String>();
    BufferedReader br = null;
    try {
      FileReader fr = new FileReader(f);
      br = new BufferedReader(fr);
      String s = br.readLine();
      while (s != null) {
        result.add(s);
        s = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return result;
  }

}
