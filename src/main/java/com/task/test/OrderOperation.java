package com.task.test;

public class OrderOperation extends QueryOperation {
	
	public OrderOperation(final ResultTable resultTable) {
		super(resultTable);
	}
	
	@Override
	String doWork(final String[] tokens) throws Exception {
		if (tokens.length != 2) {
			System.err.println(INVALID_REQUEST);
			throw new Exception(INVALID_REQUEST);
		}
		
		final ResultTableObject objectToUpdate;
		if ("buy".equals(tokens[0])) {
			objectToUpdate = this.resultTable.maximumA();
		}
		else if ("sell".equals(tokens[0])) {
			objectToUpdate = this.resultTable.minimumB();
		}
		else {
			return ERROR;
		}
		
		objectToUpdate.setSize(objectToUpdate.getSize() - Integer.parseInt(tokens[1]));
		return objectToUpdate.toString();
	}
}