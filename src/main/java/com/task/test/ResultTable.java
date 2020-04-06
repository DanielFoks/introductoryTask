package com.task.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResultTable {
	private final Map<Integer, ResultTableObject> aMap = new TreeMap<>(Collections.reverseOrder());
	private final Map<Integer, ResultTableObject> bMap = new TreeMap<>(Collections.reverseOrder());
	private final Map<Integer, ResultTableObject> sMap = new HashMap<>();
	
	public boolean update(final ResultTableObject resultTableObject) {
		if (!resultTableObject.isValidForUpdate()) {
			System.err.println(String.format("Invalid object {%s}", resultTableObject));
			return false;
		}
		final ResultTableObject tmp;
		
		if (resultTableObject.getType() == ResultTableObject.Type.A) {
			tmp = this.minimumB();
			if (tmp == null || tmp.getPrice() < resultTableObject.getPrice()) {
				this.aMap.put(resultTableObject.getPrice(), resultTableObject);
			}
			// else do smth
		}
		else if (resultTableObject.getType() == ResultTableObject.Type.B) {
			tmp = this.maximumA();
			if (tmp == null || tmp.getPrice() > resultTableObject.getPrice()) {
				this.bMap.put(resultTableObject.getPrice(), resultTableObject);
			}
			// else do smth
		}
		else {
			this.sMap.put(resultTableObject.getPrice(), resultTableObject);
		}
		return true;
	}
	
	
	public ResultTableObject maximumA() {
		if (this.aMap.isEmpty()) {
			return null;
		}
		return new ArrayList<>(this.aMap.values()).get(this.aMap.size() - 1);
	}
	
	public ResultTableObject minimumB() {
		if (this.bMap.isEmpty()) {
			return null;
		}
		return this.bMap.values().iterator().next();
	}
	
	public ResultTableObject getBySize(final int size) {
		if (this.aMap.get(size) != null) {
			return this.aMap.get(size);
		}
		else if (this.bMap.get(size) != null) {
			return this.bMap.get(size);
		}
		else if (this.sMap.get(size) != null) {
			return this.sMap.get(size);
		}
		else {
			return null;
		}
	}
}