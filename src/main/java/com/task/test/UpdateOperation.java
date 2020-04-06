package com.task.test;

public class UpdateOperation extends QueryOperation {
	public UpdateOperation(final ResultTable resultTable) {
		super(resultTable);
	}
	
	@Override
	public String doWork(final String[] tokens) throws Exception {
		if (tokens.length != 3) {
			System.err.println(INVALID_REQUEST);
			throw new Exception(INVALID_REQUEST);
		}
		final ResultTableObject resultTableObject = new ResultTableObject();
		
		try {
			resultTableObject.setPrice(Integer.parseInt(tokens[0]));
			resultTableObject.setSize(Integer.parseInt(tokens[1]));
		}
		catch (final NumberFormatException e) {
			System.err.println(INVALID_REQUEST + " : " + e);
			return ERROR;
		}
		resultTableObject.setType(ResultTableObject.Type.convertType(tokens[2]));
		
		if (this.resultTable.update(resultTableObject)) {
			return resultTableObject.toString() + " was updated.";
		}
		
		return ERROR;
	}
}
