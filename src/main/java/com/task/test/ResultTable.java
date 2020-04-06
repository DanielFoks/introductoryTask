package com.task.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResultTable {
	
	public static final String BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK = "Best bid is always lower then best ask.";
	
	private final Map<Integer, ResultTableObject> aMap = new TreeMap<>(Collections.reverseOrder());
	private final Map<Integer, ResultTableObject> bMap = new TreeMap<>(Collections.reverseOrder());
	private final Map<Integer, ResultTableObject> sMap = new HashMap<>();
	
	public boolean update(final ResultTableObject resultTableObject) {
		if (!resultTableObject.isValidForUpdate()) {
			System.err.println(String.format("Invalid object {%s}", resultTableObject));
			return false;
		}
		
		if (resultTableObject.getType() == ResultTableObject.Type.A) {
/*			if (this.minimumB() != null && this.minimumB().getPrice() >= resultTableObject.getPrice()) {
				System.err.println(BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK);
			}*/
			this.aMap.put(resultTableObject.getPrice(), resultTableObject);
			return true;
		}
		
		if (resultTableObject.getType() == ResultTableObject.Type.B) {
/*			if (this.maximumA() != null && this.maximumA().getPrice() <= resultTableObject.getPrice()) {
				System.err.println(BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK);
			}*/
			this.bMap.put(resultTableObject.getPrice(), resultTableObject);
			return true;
		}
		
		this.sMap.put(resultTableObject.getPrice(), resultTableObject);
		return true;
	}
	
	
	public ResultTableObject maximumA() {
/*		if (this.aMap.isEmpty()) {
			return null;
		}
		return new ArrayList<>(this.aMap.values()).get(this.aMap.size() - 1);*/
		return this.aMap.values().parallelStream().reduce((first, second) -> second).orElse(null);
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