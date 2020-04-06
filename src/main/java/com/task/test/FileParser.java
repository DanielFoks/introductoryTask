package com.task.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileParser {
	private final Path inputPathFile;
	private final Path outputFile;
	private final ResultTable resultTable;
	
	private FileParser() {
		this.inputPathFile = Paths.get("inputFile.txt");
		this.outputFile = Paths.get("outputFile.txt");
		this.resultTable = new ResultTable();
	}
	
	
	public void makeResultFile() throws IOException {
		
		if (!this.inputPathFile.toFile().exists()) {
			return;
		}
		
		final List<String> outputList = new LinkedList<>();
		try (final BufferedReader bufferedReader1 = Files.newBufferedReader(this.inputPathFile)) {
			String line1 = bufferedReader1.readLine();
			
			while (line1 != null) {
				this.parseLine(line1, outputList);
				line1 = bufferedReader1.readLine();
			}
		}
		
		Files.write(this.outputFile, outputList);
	}
	
	private void parseLine(final String line, final List<String> outputList) {
		if (line != null) {
			if (!line.contains(",")) {
				System.err.println("Invalid line.");
				return;
			}
			
			final String[] tokens = line.split(",");
			
			//EXCEPTION HANDLER
			try {
				this.checkOperationAndDoWork(tokens, outputList);
			}catch (final Exception e){
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	private void checkOperationAndDoWork(final String[] tokens, final List<String> outputList) throws Exception{
		final QueryOperation queryOperation;
		switch (tokens[0]) {
			case "q":
				queryOperation = new SearchOperation(this.resultTable);
				outputList.add(queryOperation.doWork(Arrays.copyOfRange(tokens, 1, tokens.length)));
				return;
			case "o":
				queryOperation = new OrderOperation(this.resultTable);
				break;
			case "u":
				queryOperation = new UpdateOperation(this.resultTable);
				break;
			default:
				System.err.println("Invalid operation type.");
				return;
		}
		queryOperation.doWork(Arrays.copyOfRange(tokens, 1, tokens.length));
	}
	
	
	public static void main(final String[] args) throws IOException {
		final FileParser fileParser = new FileParser();
		fileParser.makeResultFile();
	}
}