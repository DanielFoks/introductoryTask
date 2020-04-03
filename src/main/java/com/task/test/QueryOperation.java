package com.task.test;

public abstract class QueryOperation {
	public static final String ERROR = "Error";
	public static final String INVALID_REQUEST = "Invalid request.";
	protected ResultTable resultTable;
	
	public QueryOperation(final ResultTable resultTable) {
		this.resultTable = resultTable;
	}
	
	abstract String doWork(String[] tokens);
}
