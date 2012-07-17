package com.mukhar.wk4;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import com.mukhar.commons.DirectedEdge;
import com.mukhar.commons.LineDataReader;
import com.mukhar.commons.Node;

public class DataReader {
	/**
	 * Read the data file and build the list of nodes and edges.<br>
	 * 
	 * 
	 * @param f
	 *          The data file
	 * @param nodes
	 *          The collection of nodes, indexed by node id
	 * @param edges
	 *          The collection of edges
	 */
	public void readFile(File f, Hashtable<Integer, Node> nodes, Hashtable<Integer, DirectedEdge> edges) {
		Logger logger = Logger.getLogger("com.mukhar.wk4");
		LineDataReader ldr = new LineDataReader();
		ldr.openFile(f);
		ArrayList<String> lines = ldr.readFile(32768);
		
		while (lines.size() > 0) {
			for (String line : lines) {
				if (line.startsWith("#"))
					continue;

				String[] vals = line.split("\\s");
				int tailID = Integer.parseInt(vals[0]);
				int headID = Integer.parseInt(vals[1]);

				if (nodes.get(tailID) == null) {
					logger.info("Creating node " + tailID + "\n");
					Node n = new Node(tailID);
					nodes.put(tailID, n);

					DirectedEdge e = new DirectedEdge(tailID);
					edges.put(tailID, e);
				}

				if (nodes.get(headID) == null) {
					Node n = new Node(headID);
					nodes.put(headID, n);

					DirectedEdge e = new DirectedEdge(headID);
					edges.put(headID, e);
				}

				DirectedEdge e = edges.get(tailID);
				e.add(headID);
			}
			lines = ldr.readFile(1000);
		}

		ldr.closeFile();
	}
}
