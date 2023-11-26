package com.dejim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JSONLUtility {
	
	public JSONLUtility() {
		
	}
	
	public static String convertCSVtoJSONL(InputStream csvInputStream) throws IOException {

		StringBuilder jsonlString = new StringBuilder();

		// Create BufferedReader to read CSV content
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvInputStream))) {
            // Read the header
            String headerLine = br.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                // Split the CSV record using StringTokenizer
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                ObjectNode jsonNode = new ObjectMapper().createObjectNode();

                // Populate JSON object with CSV data
                for (String header : headers) {
                    if (tokenizer.hasMoreTokens()) {
                        jsonNode.put(header, tokenizer.nextToken());
                    }
                }

                // Convert JSON object to JSON string and append to the result
                jsonlString.append(jsonNode.toString()).append("\n");
            }
        }

        return jsonlString.toString();
	}
}
