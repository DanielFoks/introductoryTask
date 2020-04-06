package com.task.test;

public class SearchOperation extends QueryOperation {
	
	public SearchOperation(final ResultTable resultTable) {
		super(resultTable);
	}
	
	@Override
	String doWork(final String[] tokens) throws Exception{
		if (tokens.length != 1 && tokens.length != 2) {
			System.err.println(INVALID_REQUEST);
			throw new Exception(INVALID_REQUEST);
		}
		
		if ("best_bid".equals(tokens[0])) {
			return this.resultTable.minimumB().toString();
		}
		else if ("best_ask".equals(tokens[0])) {
			return this.resultTable.maximumA().toString();
		}
		else if ("size".equals(tokens[0])) {
			return this.resultTable.getBySize(Integer.parseInt(tokens[1])).getSize().toString();
		}
		else {
			return ERROR;
		}
	}
}
