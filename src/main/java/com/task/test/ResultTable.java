package com.task.test;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ResultTable extends TreeMap<Integer, ResultTableObject> {
	
	public static final String BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK = "Best bid is always lower then best ask.";
	
	public ResultTable() {
		super(Collections.reverseOrder());
	}
	
	public boolean update(final ResultTableObject resultTableObject) {
		if (resultTableObject.isValidForUpdate()) {
			if (resultTableObject.getType() == ResultTableObject.Type.A) {
				if (this.minimumB() != null && this.minimumB() >= resultTableObject.getPrice()) {
					System.err.println(BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK);
					return false;
				}
			}
			
			
			if (resultTableObject.getType() == ResultTableObject.Type.B) {
				if (this.maximumA() != null && this.maximumA() <= resultTableObject.getPrice()) {
					System.err.println(BEST_BID_IS_ALWAYS_LOWER_THEN_BEST_ASK);
					return false;
				}
			}
			
			this.put(resultTableObject.getPrice(), resultTableObject);
			return true;
		}
		System.err.println(String.format("Invalid object {%s}", resultTableObject));
		return false;
	}
	
	
	public Integer maximumA() {
		final List<Integer> filteredA = this.filteredByTypeList(ResultTableObject.Type.A);
		if (filteredA.isEmpty()) {
			return null;
		}
		return filteredA.get(filteredA.size() - 1);
	}
	
	public Integer minimumB() {
		final List<Integer> filteredB = this.filteredByTypeList(ResultTableObject.Type.B);
		if (filteredB.isEmpty()) {
			return null;
		}
		return filteredB.get(0);
	}
	
	private List<Integer> filteredByTypeList(final ResultTableObject.Type type) {
		return this.values().parallelStream().filter(o -> o.getType() == type).map(ResultTableObject::getPrice).collect(Collectors.toList());
	}
}
