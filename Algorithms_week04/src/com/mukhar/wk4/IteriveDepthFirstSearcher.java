package com.mukhar.wk4;

import java.io.File;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.*;

import com.mukhar.commons.DirectedEdge;
import com.mukhar.commons.FilePicker;
import com.mukhar.commons.Node;
import com.mukhar.wk4.DataReader;

public class IteriveDepthFirstSearcher {
	private Hashtable<Integer, Node> nodes = new Hashtable<Integer, Node>(1313377);
	private Hashtable<Integer, DirectedEdge> edges = new Hashtable<Integer, DirectedEdge>(1313377);

	private Logger logger;
	private int fValue;

	public IteriveDepthFirstSearcher() {
		logger = Logger.getLogger("com.mukhar.wk4");

		ConsoleHandler myHandler = new ConsoleHandler();
		myHandler.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				return record.getMessage();
			}
		});
		logger.setUseParentHandlers(false);
		logger.addHandler(myHandler);
		logger.setLevel(Level.OFF);

		File f = FilePicker.selectFile(".");
		DataReader dr = new DataReader();
		dr.readFile(f, nodes, edges);
	}

	public void search() {
		fValue = nodes.size();
		
		Iterator<Integer> keys = nodes.keySet().iterator();
		while (keys.hasNext()) {
			Integer key = keys.next();
			Node node = nodes.get(key);
			if (node.getFValue() == -1) {
				// if a new node has -1 has f value, it has not been explored at all
				// fvalue of 0 indicates node is on stack
				node.setFValue(0);
				doSearch(node);
			}
		}
	}

	private void doSearch(Node node) {
		Stack<Node> unexplored = new Stack<Node>();
		unexplored.ensureCapacity(74715);
		unexplored.push(node);

		while (unexplored.size() > 0) {
			int size = unexplored.size();
			node = unexplored.peek();

			// get the edges and process them
			DirectedEdge de = edges.get(node.getId());
			for (Integer headID : de.getHeads()) {
				Node newNode = nodes.get(headID);
				if (newNode.getFValue() == -1) {
					// if a new node has -1 has f value, it has not been explored at all
					// fvalue of 0 indicates node is on stack
					newNode.setFValue(0);
					unexplored.push(newNode);
				}
			}

			// if sizes are equal, no nodes were added, so set the f value of current node
			if (size == unexplored.size()) {
				node = unexplored.pop();
				node.setFValue(fValue--);
				logger.info("Setting node " + node.getId() + " fValue to "
				    + node.getFValue()+"\n");
			}
		}
	}

	private void complete() {
		logger.setLevel(Level.INFO);
		String msg ="Node id: {0,number,######}; fValue: {1,number,######}\n";
		
		for (Node n : nodes.values()) {
			logger.info(MessageFormat.format(msg, n.getId(), n.getFValue()));
		}
	}

	public static void main(String[] args) {
		IteriveDepthFirstSearcher dfs = new IteriveDepthFirstSearcher();
		dfs.search();
		dfs.complete();
	}
}
