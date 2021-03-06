package com.mukhar.commons;

import java.util.ArrayList;

public class DirectedEdge {

	int tailID;
	ArrayList<Integer> heads = new ArrayList<Integer>();

	public DirectedEdge(int id1) {
		tailID = id1;
	}

	public boolean add(int id) {
		Integer val = new Integer(id);
		return heads.add(val);
	}

	public boolean remove(int id) {
		Integer val = new Integer(id);
		return heads.remove(val);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getHeads() {
		ArrayList<Integer> clone = (ArrayList<Integer>) heads.clone();
		return clone;
	}

	public int getNumHeads() {
		return heads.size();
	}
}
