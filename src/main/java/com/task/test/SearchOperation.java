package com.task.test;

public class SearchOperation extends QueryOperation {
	
	public SearchOperation(final ResultTable resultTable) {
		super(resultTable);
	}
	
	@Override
	String doWork(final String[] tokens) {
		if (tokens.length != 1 && tokens.length != 2) {
			System.err.println(INVALID_REQUEST);
			return ERROR;
		}
		
		if ("best_bid".equals(tokens[0])) {
			return this.resultTable.get(this.resultTable.minimumB()).toString();
		}
		else if ("best_ask".equals(tokens[0])) {
			return this.resultTable.get(this.resultTable.maximumA()).toString();
		}
		else if ("size".equals(tokens[0])) {
			try {
				return this.resultTable.get(Integer.parseInt(tokens[1])).getSize().toString();
			}catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
				System.err.println(e.getLocalizedMessage());
				return ERROR;
			}
		}
		else {
			return ERROR;
		}
	}
}
