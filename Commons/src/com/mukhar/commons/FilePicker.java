package com.mukhar.commons;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Class that displays a file chooser for the user.
 */
public class FilePicker {
  /**
   * Display a JFileChooser for the user to select a file. No error checking is
   * performed.
   * 
   * @return The file chosen by the user.
   */
  public static File selectFile()
  {
    JFileChooser fc = new JFileChooser();
    fc.showOpenDialog(null);
    return fc.getSelectedFile();
  }

}
