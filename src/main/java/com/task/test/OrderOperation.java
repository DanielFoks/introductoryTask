package com.task.test;

public class OrderOperation extends QueryOperation {
	
	public OrderOperation(final ResultTable resultTable) {
		super(resultTable);
	}
	
	@Override
	String doWork(final String[] tokens) {
		if (tokens.length != 2) {
			System.err.println(INVALID_REQUEST);
			return ERROR;
		}
		
		final ResultTableObject objectToUpdate;
		if ("buy".equals(tokens[0])) {
			objectToUpdate = this.resultTable.get(this.resultTable.maximumA());
		}
		else if ("sell".equals(tokens[0])) {
			objectToUpdate = this.resultTable.get(this.resultTable.minimumB());
		}
		else {
			return ERROR;
		}
		
		
		try {
			objectToUpdate.setSize(objectToUpdate.getSize() - Integer.parseInt(tokens[1]));
		}catch (final NumberFormatException e){
			System.err.println(e.getLocalizedMessage());
			return ERROR;
		}
		
		return objectToUpdate.toString();
	}
}