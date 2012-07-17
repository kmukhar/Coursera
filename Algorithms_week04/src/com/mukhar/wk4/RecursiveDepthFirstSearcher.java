package com.mukhar.wk4;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.*;

import com.mukhar.commons.DirectedEdge;
import com.mukhar.commons.FilePicker;
import com.mukhar.commons.Node;
import com.mukhar.wk4.DataReader;

public class RecursiveDepthFirstSearcher {
	private Hashtable<Integer, Node> nodes = new Hashtable<Integer, Node>();
	private Hashtable<Integer, DirectedEdge> edges = new Hashtable<Integer, DirectedEdge>();
	private HashMap<Integer, Node> explored = new HashMap<Integer, Node>();
	private Logger logger;
	private int depth=0;

	public RecursiveDepthFirstSearcher() {
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

		File f = FilePicker
		    .selectFile(".");
		DataReader dr = new DataReader();
		dr.readFile(f, nodes, edges);
	}

	public void search() {
		while (nodes.size() > 0) {
			logger.info("Nodes size: " + nodes.size()+"\n");
			Iterator<Integer> keys = nodes.keySet().iterator();
			Integer key = keys.next();
			search(key);
		}
	}

	private void search(Integer key) {
		Node node = nodes.get(key);
		explored.put(key, node);

		DirectedEdge de = edges.get(key);
		if (de.getNumHeads() > 0) {
			for (Integer headID : de.getHeads()) {
				if (!explored.containsKey(headID)) {
					search(headID);
				}
			}
		}
		node.setFValue(nodes.size());
		nodes.remove(key);
	}

	public static void main(String[] args) {
		RecursiveDepthFirstSearcher dfs = new RecursiveDepthFirstSearcher();
		dfs.search();
		dfs.complete();
	}

	private void complete() {
		for (Node n : explored.values()) {
			logger
			    .info("Node id: " + n.getId() + "; fValue: " + n.getFValue() + "\n");
		}
	}
}
